package com.assignment.pokemon.data.remote.source.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.withTransaction
import com.assignment.pokemon.data.local.PokemonDatabase
import com.assignment.pokemon.data.local.entity.PokemonList
import com.assignment.pokemon.data.model.mapper.PokemonPaging
import com.assignment.pokemon.data.model.mapper.mapPaging
import com.assignment.pokemon.data.remote.api.ApiCallback
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase

class PokemonPagingSource(
    private val apiCallback: ApiCallback,
    private val pokemonDatabase: PokemonDatabase
): PagingSource<Int, PokemonPaging>() {
    lateinit var data: List<PokemonPaging>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonPaging> {
        return try {
            val nextPage = params.key ?: 0

            val response = apiCallback.requestPokemonList(25, nextPage)
            data = response.body()?.results.orEmpty().map { item ->
                val itemUrl = item.url.orEmpty()
                val trimmedUrl = itemUrl.dropLast(1).substringAfterLast("/")

                val result = apiCallback.requestPokemon(trimmedUrl.toInt())
                result.body()?.mapPaging(trimmedUrl) ?: PokemonPaging()
            }

            pokemonDatabase.withTransaction {
                val pokemonList = data.map {
                    PokemonList(
                        number = it.pokemonNumber,
                        name = it.pokemonName,
                        image = it.pokemonImage,
                        type1 = it.pokemonType.getOrNull(0),
                        type2 = it.pokemonType.getOrNull(1)
                    )
                }

                pokemonDatabase.pokemonListDao().insertPokemonList(pokemonList)
            }

            LoadResult.Page(
                data,
                if (nextPage == 0) null else nextPage - 25,
                if (data.isEmpty()) null else nextPage + 25
            )
        } catch (e: Exception) {
            Log.e("Error", e.toString())
            Firebase.crashlytics.recordException(e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonPaging>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}