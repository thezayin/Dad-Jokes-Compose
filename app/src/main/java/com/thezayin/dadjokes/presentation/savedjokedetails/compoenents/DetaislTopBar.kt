package com.thezayin.dadjokes.presentation.savedjokedetails.compoenents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.dadjokes.R

@Composable
fun DetailsTopBar(
    navigator: DestinationsNavigator,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.background))
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    navigator.navigateUp()
                }
        )
        Text(
            text = "Saved Jokes",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black)
        )
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.green_level_1)
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_crown),
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
