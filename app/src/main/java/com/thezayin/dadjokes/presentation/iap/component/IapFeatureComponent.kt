package com.thezayin.dadjokes.presentation.iap.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R

@Composable
fun IapFeatureComponent(modifier: Modifier) {
    Box(
        modifier = modifier.padding(top = 165.dp)
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.transparent))
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Subscribe to",
                    fontSize = 28.sp,
                    color = colorResource(id = R.color.black)
                )
                Text(
                    text = " Pro", fontSize = 28.sp,
                    style = TextStyle(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                colorResource(id = R.color.gradient_start),
                                colorResource(id = R.color.gradient_end)
                            )
                        )
                    ),
                )
            }
            Text(
                text = "Dad's Jokes: Daily Laughter Guaranteed",
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_color)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 45.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_block),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Unlock an ad-free experience",
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.text_color),
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_unlimited),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Enjoy unlimited saves with Premium",
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.text_color),
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_databases),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Enjoy unlimited Jokes with Premium",
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.text_color),
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_support),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "24/7 customer support",
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.text_color),
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
        }


    }
}