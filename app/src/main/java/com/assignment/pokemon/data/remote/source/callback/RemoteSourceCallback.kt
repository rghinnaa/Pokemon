package com.assignment.pokemon.data.remote.source.callback

import androidx.paging.PagingData
import com.assignment.pokemon.data.model.mapper.PokemonDetail
import com.assignment.pokemon.data.model.mapper.PokemonPaging
import com.assignment.pokemon.data.model.mapper.PokemonSpeciesDetail
import com.assignment.pokemon.data.remote.Result
import kotlinx.coroutines.flow.Flow

interface RemoteSourceCallback {

    fun requestPokemonList(): Flow<PagingData<PokemonPaging>>

    fun requestPokemon(pokemonId: Int): Flow<Result<PokemonDetail>>

    fun requestPokemonSpecies(pokemonId: Int): Flow<Result<PokemonSpeciesDetail>>

}