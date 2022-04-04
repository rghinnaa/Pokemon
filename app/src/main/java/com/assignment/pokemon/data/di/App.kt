package com.assignment.pokemon.data.di

import android.app.Application
import com.assignment.pokemon.data.di.DatabaseModule.databaseModule
import com.assignment.pokemon.data.di.NetworkModule.networkModule
import com.assignment.pokemon.data.di.RepositoryModule.repositoryModule
import com.assignment.pokemon.data.di.SourceModule.sourceModule
import com.assignment.pokemon.data.di.ViewModelModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    repositoryModule,
                    viewModelModule,
                    sourceModule
                )
            )
        }

    }

}