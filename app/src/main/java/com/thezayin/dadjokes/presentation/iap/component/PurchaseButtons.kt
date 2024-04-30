package com.thezayin.dadjokes.presentation.iap.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R

@Preview
@Composable
fun PurchaseButtons() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.white)
                )
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Column {
                Text(
                    text = "Annual Subscription",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.text_color)
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "$19.99/year",
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.black)
                )
            }
        }
    }
}