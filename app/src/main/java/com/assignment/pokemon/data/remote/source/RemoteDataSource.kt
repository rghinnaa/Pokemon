package com.assignment.pokemon.data.remote.source

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.assignment.pokemon.data.local.PokemonDatabase
import com.assignment.pokemon.data.model.mapper.mapDetail
import com.assignment.pokemon.data.model.mapper.mapSpecies
import com.assignment.pokemon.data.remote.api.ApiCallback
import com.assignment.pokemon.data.remote.source.paging.PokemonPagingSource
import com.assignment.pokemon.utils.flowResponse
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class RemoteDataSource(apiCallback: ApiCallback, pokemonDatabase: PokemonDatabase) {
    private val callback = apiCallback
    private val database = pokemonDatabase

    fun requestPokemonList() = Pager(
        config = PagingConfig(10)

    ) {
        PokemonPagingSource(callback, database)
    }
        .flow
        .flowOn(Dispatchers.IO)
        .catch { throwable ->
            Log.e("Error", throwable.message.toString())
            Firebase.crashlytics.recordException(throwable)
        }

    fun requestPokemon(pokemonId: Int) = flowResponse {
        Response.success(callback.requestPokemon(pokemonId).body()?.mapDetail())
    }

    fun requestPokemonSpecies(pokemonId: Int) = flowResponse {
        Response.success(callback.requestPokemonSpecies(pokemonId).body()?.mapSpecies())
    }


    // Unused due to other implementation

    /*@OptIn(ExperimentalPagingApi::class)
    fun requestPokemonList(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            remoteMediator = PokemonListMediator(
                callback,
                database
            ),
            pagingSourceFactory = { database.pokemonListDao().searchList(query) }
        )
            .flow
            .flowOn(Dispatchers.IO)
            .catch { throwable ->
                Log.e("Error", throwable.message.toString())
            }*/

}