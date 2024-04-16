package com.thezayin.dadjokes.presentation.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.domain.remote.model.JokesModel

@Composable
fun JokeTextCard(joke: JokesModel, modifier: Modifier) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxHeight()) {
            Text(
                text = joke.joke.ifEmpty {
                    "Check your internet connection and try again."
                },
                modifier = Modifier
                    .widthIn(200.dp)
                    .align(Alignment.Center)
                    .padding(30.dp),
                fontSize = 28.sp,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = LocalTextStyle.current.merge(
                    TextStyle(
                        lineHeight = 1.1.em,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.None
                        )
                    )
                )
            )
        }
    }
}