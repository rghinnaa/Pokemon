package com.assignment.pokemon.data.di

import com.assignment.pokemon.data.repository.MainRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object RepositoryModule {

    val repositoryModule = module {
        singleOf(::MainRepository)
    }

}