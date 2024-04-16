package com.thezayin.dadjokes.presentation.application

import android.app.Application
import com.google.firebase.FirebaseApp
import com.thezayin.dadjokes.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}