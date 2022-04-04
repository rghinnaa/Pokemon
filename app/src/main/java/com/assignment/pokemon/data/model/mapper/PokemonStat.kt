package com.assignment.pokemon.data.model.mapper

import com.assignment.pokemon.utils.Const.emptyInt
import com.assignment.pokemon.utils.Const.emptyString

data class PokemonStat (
    val name: String? = emptyString,
    val baseStat: Int? = emptyInt
)