package com.assignment.pokemon.data.di

import com.assignment.pokemon.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object ViewModelModule {

    val viewModelModule = module {
        viewModelOf(::MainViewModel)
    }

}