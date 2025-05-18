package com.thezayin.dadjokes.screens.saved.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel

@Dao
interface Dao {
    @Query("SELECT * FROM jokes_table")
    fun getAllJokes(): List<JokesModel>

    @Query("DELETE FROM jokes_table")
    suspend fun deleteAllJokes()

    @Query("SELECT * FROM jokes_table WHERE id = :id")
    suspend fun getJokeById(id: String): JokesModel

    @Query("DELETE FROM jokes_table WHERE id = :id")
    suspend fun deleteJokeById(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveJoke(data: JokesModel)
}