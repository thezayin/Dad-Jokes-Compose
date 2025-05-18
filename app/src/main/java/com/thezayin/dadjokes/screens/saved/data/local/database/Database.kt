package com.thezayin.dadjokes.screens.saved.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thezayin.dadjokes.screens.saved.data.local.dao.Dao
import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel

@Database(
    entities = [JokesModel::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun dao(): Dao
}