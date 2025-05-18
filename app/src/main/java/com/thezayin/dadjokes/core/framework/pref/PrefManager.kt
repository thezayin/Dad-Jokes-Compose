package com.thezayin.dadjokes.core.framework.pref

import android.content.Context
import androidx.core.content.edit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PrefManager(context: Context) {

    companion object {
        private const val KEY_IS_FIRST_TIME = "is_first_time"
    }

    private val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    private val _isFirstTime = MutableStateFlow(true)
    val isFirstTime: StateFlow<Boolean> = _isFirstTime

    init {
        _isFirstTime.value = sharedPreferences.getBoolean(KEY_IS_FIRST_TIME, true)
    }


    fun setOnboardingCompleted() {
        sharedPreferences.edit {
            putBoolean(KEY_IS_FIRST_TIME, false)
        }
        _isFirstTime.value = false
    }
}
