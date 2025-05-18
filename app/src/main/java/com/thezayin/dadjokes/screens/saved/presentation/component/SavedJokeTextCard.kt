package com.thezayin.dadjokes.screens.saved.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun SavedJokeTextCard(jokesModel: JokesModel, callBack: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(60.sdp)
            .padding(horizontal = 15.sdp, vertical = 5.sdp),
        onClick = { callBack() },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.background)
        ),
        shape = RoundedCornerShape(20.sdp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.sdp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = jokesModel.joke,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.ssp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
            )
        }
    }
}