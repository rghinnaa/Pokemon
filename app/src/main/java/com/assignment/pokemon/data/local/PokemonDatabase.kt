package com.assignment.pokemon.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.pokemon.data.local.dao.FavoriteDao
import com.assignment.pokemon.data.local.dao.PokemonListDao
import com.assignment.pokemon.data.local.dao.PokemonListKeysDao
import com.assignment.pokemon.data.local.entity.PokemonFavorite
import com.assignment.pokemon.data.local.entity.PokemonList
import com.assignment.pokemon.data.local.entity.PokemonListKeys

@Database(
    entities = [PokemonFavorite::class, PokemonList::class, PokemonListKeys::class],
    version = 4,
    exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun pokemonListDao(): PokemonListDao
    abstract fun pokemonListKeysDao(): PokemonListKeysDao
}