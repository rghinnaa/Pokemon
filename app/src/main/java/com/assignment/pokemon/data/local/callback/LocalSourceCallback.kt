package com.assignment.pokemon.data.local.callback

import com.assignment.pokemon.data.local.entity.PokemonFavorite
import com.assignment.pokemon.data.local.entity.PokemonList
import com.assignment.pokemon.data.model.mapper.PokemonPaging
import kotlinx.coroutines.flow.Flow

interface LocalSourceCallback {

    fun getFavorite(): Flow<List<PokemonFavorite>>

    suspend fun insertFavorite(pokemonFavorite: PokemonFavorite)

    suspend fun deleteFavorite(number: Int?)

    suspend fun insertPokemonList(pokemonList: List<PokemonList>)

    fun searchPokemonList(query: String): Flow<List<PokemonList>>

}