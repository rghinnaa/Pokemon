package com.assignment.pokemon.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assignment.pokemon.utils.Const.Database.Table.POKEMON_LIST_KEY

/* Unused due to other implementation */

@Entity(tableName = POKEMON_LIST_KEY)
data class PokemonListKeys(
    @PrimaryKey
    val listId: Int?,
    val prevKey: Int?,
    val nextKey: Int?
)