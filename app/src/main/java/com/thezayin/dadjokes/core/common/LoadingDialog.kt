package com.thezayin.dadjokes.core.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.thezayin.dadjokes.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun LoadingDialog() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.sdp)
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .size(50.sdp),
            shape = RoundedCornerShape(50.sdp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.card_background)
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    strokeWidth = 2.sdp,
                    modifier = Modifier
                        .size(25.sdp),
                    color = colorResource(id = R.color.black)
                )
            }
        }
    }
}