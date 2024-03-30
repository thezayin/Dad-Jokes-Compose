package com.thezayin.dadjokes.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import com.thezayin.dadjokes.presentation.NavGraphs
import com.thezayin.dadjokes.ui.theme.DadJokesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DadJokesTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

