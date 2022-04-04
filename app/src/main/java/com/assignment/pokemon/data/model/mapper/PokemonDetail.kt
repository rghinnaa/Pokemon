package com.assignment.pokemon.data.model.mapper

import com.assignment.pokemon.utils.Const.emptyInt
import com.assignment.pokemon.utils.Const.emptyString

data class PokemonDetail (
    val pokemonName: String = emptyString,
    val pokemonNumber: Int = emptyInt,
    val pokemonImage: String = emptyString,
    val pokemonType: List<String> = emptyList(),
    val pokemonAbility: List<String> = emptyList(),
    val pokemonWeight: Int = emptyInt,
    val pokemonHeight: Int = emptyInt,
    val pokemonStats: List<PokemonStat> = emptyList()
)