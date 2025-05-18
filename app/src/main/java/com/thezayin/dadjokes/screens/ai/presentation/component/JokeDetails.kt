package com.thezayin.dadjokes.screens.ai.presentation.component

import DynamicResizableTextField
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import com.thezayin.dadjokes.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun JokeDetails(
    descriptionTitle: String,
    jokePlaceholder: String,
    onJokeChange: (String) -> Unit,
) {
    var descriptionText by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.sdp, horizontal = 15.sdp)
    ) {
        Text(
            text = descriptionTitle,
            fontSize = 8.ssp,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
            color = colorResource(id = R.color.text_color),
            fontWeight = FontWeight.Bold
        )

        DynamicResizableTextField(
            descriptionText = descriptionText,
            onJokeChange = { newText ->
                descriptionText = newText
                onJokeChange(newText.text)
            },
            jokePlaceholder = jokePlaceholder
        )
    }
}