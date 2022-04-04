package com.assignment.pokemon.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.pokemon.data.local.entity.PokemonFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM POKEMON_FAVORITE_ENTITY ORDER BY id DESC")
    fun getFavorite(): Flow<List<PokemonFavorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: PokemonFavorite)

    @Query("DELETE FROM POKEMON_FAVORITE_ENTITY WHERE number = :number")
    suspend fun deleteFavorite(number: Int?)

}