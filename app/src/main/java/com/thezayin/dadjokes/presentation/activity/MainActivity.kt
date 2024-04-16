package com.thezayin.dadjokes.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.messaging.FirebaseMessaging
import com.ramcosta.composedestinations.DestinationsNavHost
import com.thezayin.dadjokes.presentation.NavGraphs
import com.thezayin.dadjokes.presentation.theme.DadJokesTheme
import com.thezayin.dadjokes.presentation.utils.Constants.TOPIC

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        setContent {
            DadJokesTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

