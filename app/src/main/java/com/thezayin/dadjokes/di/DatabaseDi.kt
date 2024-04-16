package com.thezayin.dadjokes.di

import android.content.Context
import androidx.room.Room
import com.thezayin.dadjokes.data.local.database.Database

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, Database::class.java, "jokes_database")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun provideDao(database: Database) = database.dao()