package com.thezayin.dadjokes.presentation.home.component

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.domain.remote.model.JokesModel
import com.thezayin.dadjokes.presentation.savedjokes.SaveViewModel
import com.thezayin.dadjokes.presentation.utils.copyText
import com.thezayin.dadjokes.presentation.utils.share
import com.thezayin.dadjokes.presentation.utils.textToSpeech
import java.util.Locale

@Composable
fun CardBottomButtons(
    jokesModel: JokesModel,
    saveViewModel: SaveViewModel,
    context: Context,
    modifier: Modifier
) {
    lateinit var textToSpeech: TextToSpeech
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            colorResource(id = R.color.background)
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (jokesModel.joke.isNotEmpty()) {
                        saveViewModel.saveJoke(jokesModel)
                        Toast
                            .makeText(context, "Joke Saved", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(150.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.background),
                    contentColor = colorResource(id = R.color.icon_color)
                )
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_bookmark_unsave
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }

            Button(
                onClick = {
                    context.copyText(jokesModel.joke)
                },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(150.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.background),
                    contentColor = colorResource(id = R.color.icon_color)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_copy),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }

            Button(
                onClick = {
                    context.share(jokesModel.joke)
                },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(150.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.background),
                    contentColor = colorResource(id = R.color.icon_color)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }

            Button(
                onClick = {
                    textToSpeech(jokesModel.joke, textToSpeech)
                },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(150.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.background),
                    contentColor = colorResource(id = R.color.icon_color)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                textToSpeech = TextToSpeech(context) { status ->
                    if (status == TextToSpeech.SUCCESS) {
                        val result = textToSpeech.setLanguage(Locale.US)
                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.d("Voice Play", "Language not supported")
                        }
                    } else {
                        Log.d("Voice Play", "Initialization Failed")
                    }
                }
            }
        }
    }
}