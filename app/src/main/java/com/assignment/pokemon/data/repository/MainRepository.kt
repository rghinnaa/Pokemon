package com.assignment.pokemon.data.repository

import androidx.paging.PagingData
import com.assignment.pokemon.data.local.LocalDataSource
import com.assignment.pokemon.data.local.callback.LocalSourceCallback
import com.assignment.pokemon.data.local.entity.PokemonFavorite
import com.assignment.pokemon.data.local.entity.PokemonList
import com.assignment.pokemon.data.model.mapper.PokemonPaging
import com.assignment.pokemon.data.remote.source.RemoteDataSource
import com.assignment.pokemon.data.remote.source.callback.RemoteSourceCallback
import kotlinx.coroutines.flow.Flow

class MainRepository(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) : RemoteSourceCallback, LocalSourceCallback {
    private val dataSource = remoteDataSource
    private val localSource = localDataSource

    override fun requestPokemonList(): Flow<PagingData<PokemonPaging>> =
        dataSource.requestPokemonList()

    override fun requestPokemon(pokemonId: Int) =
        dataSource.requestPokemon(pokemonId)

    override fun requestPokemonSpecies(pokemonId: Int) =
        dataSource.requestPokemonSpecies(pokemonId)

    override fun getFavorite(): Flow<List<PokemonFavorite>> = localSource.getFavorite()

    override suspend fun insertFavorite(pokemonFavorite: PokemonFavorite) =
        localSource.insertFavorite(pokemonFavorite)

    override suspend fun deleteFavorite(number: Int?) =
        localSource.deleteFavorite(number)

    override suspend fun insertPokemonList(pokemonList: List<PokemonList>) =
        localSource.insertPokemonList(pokemonList)

    override fun searchPokemonList(query: String): Flow<List<PokemonList>> =
        localSource.searchPokemonList(query)
}