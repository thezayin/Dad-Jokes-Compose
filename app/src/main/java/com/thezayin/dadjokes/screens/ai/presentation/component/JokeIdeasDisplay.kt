package com.thezayin.dadjokes.screens.ai.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.thezayin.dadjokes.screens.ai.domain.model.AIContentModel
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay

@Composable
fun JokeIdeasDisplay(
    jokeIdeas: List<AIContentModel>, onCopyClick: () -> Unit, onShareClick: () -> Unit
) {
    val displayedIdeas = remember { mutableStateListOf<AIContentModel>() }
    LaunchedEffect(jokeIdeas) {
        for (joke in jokeIdeas) {
            displayedIdeas.add(joke)
            val typingSpeedPerChar = 30L
            val totalChars = joke.title.length + joke.description.length
            val totalDelay = totalChars * typingSpeedPerChar
            delay(totalDelay)
        }
    }

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
    ) {
        displayedIdeas.forEach { joke ->
            JokeIdeaItem(
                joke = joke,
                onCopyClick = onCopyClick,
                onShareClick = onShareClick,
            )
            Spacer(modifier = Modifier.height(10.sdp))
        }
    }
}

