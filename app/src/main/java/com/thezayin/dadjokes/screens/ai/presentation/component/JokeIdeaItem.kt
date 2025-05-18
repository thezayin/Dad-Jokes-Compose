package com.thezayin.dadjokes.screens.ai.presentation.component

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.material.icons.outlined.CopyAll
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.core.framework.extension.functions.textToSpeech
import com.thezayin.dadjokes.screens.ai.domain.model.AIContentModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import kotlinx.coroutines.delay
import timber.log.Timber
import java.util.Locale

@Composable
fun JokeIdeaItem(
    joke: AIContentModel,
    onCopyClick: () -> Unit,
    onShareClick: () -> Unit
) {
    var displayedText by remember { mutableStateOf("") }
    val fullText = "${joke.title}\n\n${joke.description}"
    val context = LocalContext.current
    lateinit var textToSpeech: TextToSpeech
    LaunchedEffect(fullText) {
        for (i in 1..fullText.length) {
            displayedText = fullText.substring(0, i)
            delay(30L)
        }
    }
    Column(modifier = Modifier) {
        Text(
            text = displayedText, fontSize = 10.ssp, color = colorResource(R.color.text_color)
        )
        Spacer(modifier = Modifier.height(8.sdp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    textToSpeech(fullText, textToSpeech)
                }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.VolumeUp,
                    tint = colorResource(id = R.color.black),
                    contentDescription = null,
                    modifier = Modifier.size(12.sdp)
                )
                textToSpeech = TextToSpeech(context) { status ->
                    if (status == TextToSpeech.SUCCESS) {
                        val result = textToSpeech.setLanguage(Locale.US)
                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Timber.tag("Voice Play").d("Language not supported")
                        }
                    } else {
                        Timber.tag("Voice Play").d("Initialization Failed")
                    }
                }
            }

            IconButton(
                onClick = {
                    onCopyClick()
                }) {
                Icon(
                    modifier = Modifier.size(12.sdp),
                    imageVector = Icons.Outlined.CopyAll,
                    tint = colorResource(id = R.color.black),
                    contentDescription = null
                )
            }


            IconButton(
                onClick = {
                    onShareClick()
                }) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    tint = colorResource(id = R.color.black),
                    contentDescription = null,
                    modifier = Modifier.size(12.sdp)
                )
            }
        }
    }
}