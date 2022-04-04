package com.assignment.pokemon.data.local.mediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.assignment.pokemon.data.local.PokemonDatabase
import com.assignment.pokemon.data.local.entity.PokemonList
import com.assignment.pokemon.data.local.entity.PokemonListKeys
import com.assignment.pokemon.data.model.mapper.PokemonPaging
import com.assignment.pokemon.data.model.mapper.mapPaging
import com.assignment.pokemon.data.remote.api.ApiCallback
import retrofit2.HttpException
import java.io.IOException

/* Unused due to other implementation */

@OptIn(ExperimentalPagingApi::class)
class PokemonListMediator(
    private val apiCallback: ApiCallback,
    private val pokemonDatabase: PokemonDatabase
): RemoteMediator<Int, PokemonList>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonList>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val listKeys = getListKeyClosestToCurrentPosition(state)
                listKeys?.nextKey?.minus(25) ?: 0
            }
            LoadType.PREPEND -> {
                val listKeys = getListKeyForFirstItem(state)
                val prevKey = listKeys?.prevKey

                if(prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = listKeys != null)
                }

                prevKey
            }
            LoadType.APPEND -> {
                val listKeys = getListKeyForLastItem(state)

                val nextKey = listKeys?.nextKey

                if(nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = listKeys != null)
                }

                nextKey
            }
        }

        try {
            val apiResponse = apiCallback.requestPokemonList(25, page)

            val response = apiResponse.body()?.results.orEmpty().map { item ->
                val itemUrl = item.url.orEmpty()
                val trimmedUrl = itemUrl.dropLast(1).substringAfterLast("/")

                val result = apiCallback.requestPokemon(trimmedUrl.toInt())
                result.body()?.mapPaging(trimmedUrl) ?: PokemonPaging()
            }
            val endOfPaginationReached = response.isEmpty()

            pokemonDatabase.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    pokemonDatabase.pokemonListKeysDao().clearPokemonListKeys()
                    pokemonDatabase.pokemonListDao().clearPokemonList()
                }
                val prevKey = if(page == 0) null else page - 25
                val nextKey = if(endOfPaginationReached) null else page + 25
                val keys = response.map {
                    PokemonListKeys(listId = it.pokemonNumber, prevKey = prevKey, nextKey = nextKey)
                }
                val pokemonList = response.map {
                    PokemonList(
                        number = it.pokemonNumber,
                        name = it.pokemonName,
                        image = it.pokemonImage,
                        type1 = it.pokemonType.getOrNull(0),
                        type2 = it.pokemonType.getOrNull(1)
                    )
                }
                pokemonDatabase.pokemonListKeysDao().insertPokemonList(keys)
                pokemonDatabase.pokemonListDao().insertPokemonList(pokemonList)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            Log.e("Error", exception.toString())
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            Log.e("Error", exception.toString())
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getListKeyForLastItem(state: PagingState<Int, PokemonList>): PokemonListKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { list ->
                pokemonDatabase.pokemonListKeysDao().pokemonListKeysNumber(list.number)
            }
    }

    private suspend fun getListKeyForFirstItem(state: PagingState<Int, PokemonList>): PokemonListKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { list ->
                pokemonDatabase.pokemonListKeysDao().pokemonListKeysNumber(list.number)
            }
    }

    private suspend fun getListKeyClosestToCurrentPosition(
        state: PagingState<Int, PokemonList>
    ): PokemonListKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.number?.let { listId ->
                pokemonDatabase.pokemonListKeysDao().pokemonListKeysNumber(listId)
            }
        }
    }

}