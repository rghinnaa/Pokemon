package com.assignment.pokemon.data.di

import com.assignment.pokemon.data.remote.source.RemoteDataSource
import com.assignment.pokemon.data.local.LocalDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object SourceModule {

    val sourceModule = module {
        singleOf(::RemoteDataSource)
        singleOf(::LocalDataSource)
    }

}