package com.thezayin.dadjokes.screens.di

import android.content.Context
import androidx.room.Room
import com.thezayin.dadjokes.screens.saved.data.local.database.Database

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, Database::class.java, "jokes_database")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration(false)
        .build()

fun provideDao(database: Database) = database.dao()