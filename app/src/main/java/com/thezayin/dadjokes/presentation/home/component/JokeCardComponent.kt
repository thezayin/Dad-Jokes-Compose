package com.thezayin.dadjokes.presentation.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.dadjokes.R

@Composable
fun JokeCardComponent(joke: String, modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.Center)
                .padding(horizontal = 25.dp),
            colors = CardDefaults.cardColors(
                colorResource(id = R.color.purple)
            ),
            elevation = CardDefaults.cardElevation(5.dp),
        ) {
            Box(modifier = Modifier.fillMaxHeight()) {
                Text(
                    text = joke,
                    modifier = Modifier
                        .widthIn(200.dp)
                        .align(Alignment.Center)
                        .padding(30.dp),
                    fontSize = 22.sp,
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}