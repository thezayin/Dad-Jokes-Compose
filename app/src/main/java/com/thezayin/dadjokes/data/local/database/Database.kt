package com.thezayin.dadjokes.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thezayin.dadjokes.data.local.dao.Dao
import com.thezayin.dadjokes.domain.remote.model.JokesModel

@Database(
    entities = [JokesModel::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun dao(): Dao
}