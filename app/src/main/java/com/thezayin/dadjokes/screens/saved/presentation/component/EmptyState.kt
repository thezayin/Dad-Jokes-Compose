package com.thezayin.dadjokes.screens.saved.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.thezayin.dadjokes.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun EmptyState() {
    Card(
        shape = androidx.compose.foundation.shape.RoundedCornerShape(24.sdp),
        modifier = Modifier
            .padding(horizontal = 25.sdp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.card_background)
        ),
    ) {
        Text(
            text = "No saved jokes yet",
            fontSize = 12.ssp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.text_color),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.sdp)
        )
    }
}