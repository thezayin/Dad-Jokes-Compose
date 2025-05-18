package com.thezayin.dadjokes.screens.saved.data.repository

import android.content.Context
import androidx.core.content.edit
import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel
import com.thezayin.dadjokes.screens.saved.domain.repository.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class SessionManagerImpl(private val context: Context) : SessionManager {

    private val _jokes = MutableStateFlow<JokesModel?>(loadJokeFromPreferences())
    override val joke: StateFlow<JokesModel?> = _jokes

    override fun saveJokeId(joke: JokesModel?) {
        _jokes.value = joke
        saveJokeToPreferences(joke)
        Timber.tag("SessionManagerImpl").d("Saved joke ID: ${joke?.id}")
    }

    override fun clearSessionManager() {
        _jokes.value = null
        clearPreferences()
    }

    private fun saveJokeToPreferences(joke: JokesModel?) {
        val sharedPrefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
        sharedPrefs.edit {
            putString("saved_joke_id", joke?.id)
            putString("saved_joke_text", joke?.joke)
            putInt("saved_joke_status", joke?.status ?: 0)
        }
    }

    private fun loadJokeFromPreferences(): JokesModel? {
        val sharedPrefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val jokeId = sharedPrefs.getString("saved_joke_id", null)
        val jokeText = sharedPrefs.getString("saved_joke_text", null)
        val jokeStatus = sharedPrefs.getInt("saved_joke_status", 0)
        if (jokeId != null) {
            Timber.tag("SessionManagerImpl").d("Loaded joke ID from preferences: $jokeId")
            return JokesModel(
                jokeId, jokeText.toString(), jokeStatus
            )
        } else {
            Timber.tag("SessionManagerImpl").d("No joke found in preferences.")
            return null
        }
    }

    private fun clearPreferences() {
        val sharedPrefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
        sharedPrefs.edit {
            remove("saved_joke_id")
        }
    }
}
