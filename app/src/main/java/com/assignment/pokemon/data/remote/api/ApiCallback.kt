package com.assignment.pokemon.data.remote.api

import com.assignment.pokemon.data.model.Pokemon
import com.assignment.pokemon.data.model.PokemonList
import com.assignment.pokemon.data.model.PokemonSpecies
import com.assignment.pokemon.utils.Const.POKEMON
import com.assignment.pokemon.utils.Const.POKEMON_LIST
import com.assignment.pokemon.utils.Const.POKEMON_SPECIES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCallback {

    @GET(POKEMON_LIST)
    suspend fun requestPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonList>

    @GET(POKEMON)
    suspend fun requestPokemon(
        @Path("id") id: Int
    ): Response<Pokemon>

    @GET(POKEMON_SPECIES)
    suspend fun requestPokemonSpecies(
        @Path("id") id: Int
    ): Response<PokemonSpecies>

}