package com.thezayin.dadjokes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thezayin.framework.utils.Constants
import kotlinx.serialization.Serializable

@Entity(tableName = Constants.JOKES_TABLE)
@Serializable
data class JokesModel(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val joke: String,
    val status: Int
)