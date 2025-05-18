package com.thezayin.ads.di

import com.thezayin.ads.GoogleManager
import org.koin.dsl.module

val adModule = module {
    single { GoogleManager() }
}