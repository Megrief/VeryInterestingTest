package com.example.veryinterestingtest.app

import android.app.Application
import com.example.veryinterestingtest.app.di.dataModule
import com.example.veryinterestingtest.app.di.presentationModule
import com.example.veryinterestingtest.app.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VeryInterestingApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dataModule, repositoryModule, presentationModule)
            androidContext(this@VeryInterestingApp)
        }
    }
}