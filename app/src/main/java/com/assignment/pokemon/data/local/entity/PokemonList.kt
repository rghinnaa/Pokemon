package com.assignment.pokemon.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assignment.pokemon.utils.Const.Database.Table.POKEMON_LIST
import com.assignment.pokemon.utils.Const.emptyInt
import com.assignment.pokemon.utils.Const.emptyString

@Entity(tableName = POKEMON_LIST)
data class PokemonList (
    @PrimaryKey(autoGenerate = true)
    var number: Int? = null,
    val name: String? = null,
    val image: String? = null,
    val type1: String? = null,
    val type2: String? = null
)