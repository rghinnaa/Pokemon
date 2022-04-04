package com.assignment.pokemon.data.local

import com.assignment.pokemon.data.local.dao.FavoriteDao
import com.assignment.pokemon.data.local.dao.PokemonListDao
import com.assignment.pokemon.data.local.entity.PokemonFavorite
import com.assignment.pokemon.data.local.entity.PokemonList

class LocalDataSource(favoriteDao: FavoriteDao, pokemonListDao: PokemonListDao) {
    private val dao = favoriteDao
    private val listDao = pokemonListDao

    fun getFavorite() = dao.getFavorite()

    suspend fun insertFavorite(pokemonFavorite: PokemonFavorite) {
        dao.insertFavorite(pokemonFavorite)
    }

    suspend fun deleteFavorite(number: Int?) {
        dao.deleteFavorite(number)
    }

    suspend fun insertPokemonList(pokemonList: List<PokemonList>) {
        listDao.insertPokemonList(pokemonList)
    }

    fun searchPokemonList(query: String) = listDao.searchList(query)

}