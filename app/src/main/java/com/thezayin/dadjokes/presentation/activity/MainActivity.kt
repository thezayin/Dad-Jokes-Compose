package com.thezayin.dadjokes.presentation.activity

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.ramcosta.composedestinations.DestinationsNavHost
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.presentation.NavGraphs
import com.thezayin.dadjokes.presentation.utils.Constants.TOPIC
import com.thezayin.dadjokes.presentation.theme.DadJokesTheme

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

