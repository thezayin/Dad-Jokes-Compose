package com.thezayin.dadjokes.presentation.activity.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R


@Preview
@Composable
fun PremiumButton() {
    Box(
        modifier = Modifier
            .width(75.dp)
            .height(30.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.gradient_start_2),
                        colorResource(id = R.color.gradient_end_2)
                    )
                )
            )
    ) {
        Card(
            modifier = Modifier
                .background(colorResource(id = R.color.transparent))
                .padding(0.5.dp)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                colorResource(id = R.color.gradient_end_3),
                                colorResource(id = R.color.gradient_start_3)
                            )
                        )
                    ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.transparent)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_diamond),
                        contentDescription = null,
                        modifier = Modifier
                            .size(10.dp)
                    )
                    Text(
                        text = "Premium",
                        modifier = Modifier.padding(start = 5.dp),
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.white),
                        fontSize = 9.sp,
                        style = TextStyle(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    colorResource(id = R.color.gradient_start_2),
                                    colorResource(id = R.color.gradient_end_2)
                                )
                            )
                        ),
                    )
                }
            }
        }

    }
}