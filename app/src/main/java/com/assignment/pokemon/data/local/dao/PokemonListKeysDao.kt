package com.assignment.pokemon.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.pokemon.data.local.entity.PokemonListKeys

/* Unused due to other implementation */

@Dao
interface PokemonListKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<PokemonListKeys>)

    @Query("SELECT * FROM POKEMON_LIST_KEY_ENTITY WHERE listId = :id")
    suspend fun pokemonListKeysNumber(id: Int?): PokemonListKeys

    @Query("DELETE FROM POKEMON_LIST_KEY_ENTITY")
    suspend fun clearPokemonListKeys()

}