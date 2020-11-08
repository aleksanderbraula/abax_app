package com.braula.abaxapp

import android.app.Application
import com.braula.abaxapp.viewmodel.BeerViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

@Suppress("unused")
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        val viewModelModules = module {
            single { BeerViewModel(this@App) }
        }

        startKoin {
            modules(
                viewModelModules
            )
        }
    }

}