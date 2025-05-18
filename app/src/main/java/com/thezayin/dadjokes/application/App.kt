package com.thezayin.dadjokes.application

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.FirebaseApp
import com.thezayin.dadjokes.screens.di.aiModule
import com.thezayin.dadjokes.screens.di.analyticsModule
import com.thezayin.dadjokes.screens.di.appModule
import com.thezayin.dadjokes.screens.di.frameworkModule
import com.thezayin.dadjokes.screens.di.homeModule
import com.thezayin.dadjokes.screens.di.onBoardingModule
import com.thezayin.dadjokes.screens.di.savedModule
import com.thezayin.dadjokes.screens.di.settingModule
import com.thezayin.dadjokes.screens.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

@RequiresApi(Build.VERSION_CODES.P)
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
            modules(frameworkModule)
            modules(aiModule)
            modules(splashModule)
            modules(settingModule)
            modules(onBoardingModule)
            modules(savedModule)
            modules(homeModule)
            modules(analyticsModule)
        }
    }
}