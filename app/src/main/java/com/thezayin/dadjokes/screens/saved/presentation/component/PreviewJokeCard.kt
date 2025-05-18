package com.thezayin.dadjokes.screens.saved.presentation.component

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.core.framework.extension.functions.textToSpeech
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import timber.log.Timber
import java.util.Locale

@Composable
fun PreviewJokeCard(
    joke: String?,
    deleteJoke: () -> Unit,
) {
    val context = LocalContext.current
    lateinit var textToSpeech: TextToSpeech
    Card(
        shape = androidx.compose.foundation.shape.RoundedCornerShape(24.sdp),
        modifier = Modifier.padding(horizontal = 25.sdp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.card_background)
        ),
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier.padding(horizontal = 10.sdp, vertical = 10.sdp), onClick = {
                        textToSpeech(joke ?: "", textToSpeech)
                    }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.VolumeUp,
                        tint = colorResource(id = R.color.black),
                        contentDescription = null,
                        modifier = Modifier.size(24.sdp)
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
            }
            Text(
                text = joke?.ifEmpty { "Check your internet connection and try again." }.toString(),
                modifier = Modifier.padding(horizontal = 35.sdp, vertical = 20.sdp),
                fontSize = 16.ssp,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            )
            PreviewBottomButton(
                joke = joke, deleteJoke = deleteJoke
            )
        }
    }
}