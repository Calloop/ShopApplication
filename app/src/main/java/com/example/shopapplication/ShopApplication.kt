package com.example.shopapplication

import android.app.Application
import com.example.shopapplication.di.dataModule
import com.example.shopapplication.di.networkModule
import com.example.shopapplication.di.useCasesModule
import com.example.shopapplication.di.viewModelsModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ShopApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            modules(networkModule, dataModule, useCasesModule, viewModelsModule)
        }
    }
}