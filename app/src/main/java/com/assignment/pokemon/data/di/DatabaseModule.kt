package com.assignment.pokemon.data.di

import android.app.Application
import androidx.room.Room
import com.assignment.pokemon.data.local.PokemonDatabase
import com.assignment.pokemon.utils.Const.Database.POKEMON_DATABASE
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object DatabaseModule {

    val databaseModule = module {
        singleOf(::provideDatabase)
        singleOf(::provideFavoriteDao)
        singleOf(::providePokemonListDao)
    }

    fun provideDatabase(application: Application) = Room.databaseBuilder(
        application,
        PokemonDatabase::class.java,
        POKEMON_DATABASE
    ).fallbackToDestructiveMigration().build()

    fun provideFavoriteDao(dataBase: PokemonDatabase) = dataBase.favoriteDao()

    fun providePokemonListDao(dataBase: PokemonDatabase) = dataBase.pokemonListDao()

}