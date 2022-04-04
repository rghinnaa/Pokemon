package com.assignment.pokemon.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.pokemon.data.local.entity.PokemonList
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<PokemonList>)

    @Query("SELECT * FROM POKEMON_LIST_ENTITY WHERE " +
            "name LIKE :queryString OR number LIKE :queryString " +
            "ORDER BY number ASC")
    fun searchList(queryString: String): Flow<List<PokemonList>>

    @Query("DELETE FROM POKEMON_LIST_ENTITY")
    suspend fun clearPokemonList()

}