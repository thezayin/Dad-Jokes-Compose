package com.thezayin.dadjokes.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R
import com.thezayin.dadjokes.presentation.home.HomeViewModel

@Composable
fun NextJokeButton(viewModel: HomeViewModel, modifier: Modifier) {
    Button(
        onClick = {
            viewModel.getRandomJoke()
        },
        modifier = modifier
            .width(160.dp)
            .height(60.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.home_card_color),
        )
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Next",
                color = colorResource(id = R.color.white),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(id = R.color.gradient_start),
                            colorResource(id = R.color.gradient_end)
                        )
                    )
                ),
            )
            Image(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(25.dp)
            )
        }
    }
}
