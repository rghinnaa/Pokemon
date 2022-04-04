package com.assignment.pokemon.data.model.mapper

import com.assignment.pokemon.utils.Const.emptyInt
import com.assignment.pokemon.utils.Const.emptyString

data class PokemonPaging (
    val pokemonUrl: String = emptyString,
    val pokemonName: String = emptyString,
    val pokemonNumber: Int = emptyInt,
    val pokemonImage: String = emptyString,
    val pokemonType: List<String> = emptyList()
)