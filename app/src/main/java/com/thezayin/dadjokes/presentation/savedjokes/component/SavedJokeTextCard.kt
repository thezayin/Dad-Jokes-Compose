package com.thezayin.dadjokes.presentation.savedjokes.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.domain.remote.model.JokesModel

@Composable
fun SavedJokeTextCard(jokesModel: JokesModel, callBack: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                callBack()
            }
            .height(120.dp)
            .padding(horizontal = 20.dp, vertical = 5.dp),
        colors = CardDefaults.cardColors(
            colorResource(id = R.color.background)
        ),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, colorResource(id = R.color.grey_level_5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = jokesModel.joke,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                color = colorResource(id = R.color.black),
                fontFamily = FontFamily(Font(R.font.noto_sans_regular))
            )
        }
    }
}