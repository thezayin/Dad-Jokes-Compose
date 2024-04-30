package com.thezayin.dadjokes.presentation.home.component

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.core.R
import com.thezayin.dadjokes.domain.model.JokesModel
import com.thezayin.dadjokes.presentation.savedjokes.SaveViewModel
import com.thezayin.core.utils.textToSpeech
import timber.log.Timber
import java.util.Locale

@Composable
fun JokeTextCard(
    modifier: Modifier,
    jokeModel: JokesModel,
    saveViewModel: SaveViewModel,
    from: String,
    navigator: DestinationsNavigator,
    id: String?
) {
    val joke = jokeModel.joke
    val context = LocalContext.current
    lateinit var textToSpeech: TextToSpeech
    Card(
        modifier = modifier
            .padding(horizontal = 45.dp),
        elevation = CardDefaults.cardElevation(35.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.home_card_color)
        ),
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 15.dp),
                    shape = RoundedCornerShape(100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.transparent),
                    ),
                    onClick = { textToSpeech(joke, textToSpeech) }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    textToSpeech = TextToSpeech(context) { status ->
                        if (status == TextToSpeech.SUCCESS) {
                            val result = textToSpeech.setLanguage(Locale.US)
                            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Timber
                                    .tag("Voice Play")
                                    .d("Language not supported")
                            }
                        } else {
                            Timber
                                .tag("Voice Play")
                                .d("Initialization Failed")
                        }
                    }
                }
            }
            Text(
                text = joke.ifEmpty { "Check your internet connection and try again." },
                modifier = Modifier
                    .padding(horizontal = 35.dp)
                    .padding(top = 65.dp)
                    .widthIn(200.dp),
                fontSize = 24.sp,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            )
            CardBottomButtons(
                from = from,
                modifier = Modifier,
                joke = joke,
                callback = {
                    if (joke.isNotEmpty() && from == "home") {
                        saveViewModel.saveJoke(jokeModel)
                    } else {
                        id?.let {
                            saveViewModel.deleteJokeById(id)
                            navigator.navigateUp()
                        }
                    }
                }
            )
        }
    }
}