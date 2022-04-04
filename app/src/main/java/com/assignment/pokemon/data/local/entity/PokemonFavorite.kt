package com.assignment.pokemon.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assignment.pokemon.utils.Const.Database.Table.POKEMON_FAVORITE

@Entity(tableName = POKEMON_FAVORITE)
data class PokemonFavorite (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val name: String? = null,
    val number: Int? = null,
    val image: String? = null,
    val type1: String? = null,
    val type2: String? = null
)