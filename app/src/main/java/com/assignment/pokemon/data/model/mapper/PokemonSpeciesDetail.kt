package com.assignment.pokemon.data.model.mapper

import com.assignment.pokemon.utils.Const.emptyInt
import com.assignment.pokemon.utils.Const.emptyString

data class PokemonSpeciesDetail (
    val pokemonColor: String = emptyString,
    val pokemonEgg: List<String> = emptyList(),
    val pokemonHatch: Int = emptyInt,
    val pokemonGrowth: String = emptyString,
    val pokemonHabitat: String = emptyString,
    val pokemonShape: String = emptyString
)