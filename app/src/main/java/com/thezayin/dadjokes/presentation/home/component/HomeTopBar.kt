package com.thezayin.dadjokes.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.presentation.destinations.SavedJokesScreenDestination
import com.thezayin.dadjokes.presentation.destinations.SettingScreenDestination

@Composable
fun HomeTopBar(modifier: Modifier, navigator: DestinationsNavigator) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_setting),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    navigator.navigate(SettingScreenDestination)
                }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bookmark_saved),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navigator.navigate(SavedJokesScreenDestination)
                    }
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(start = 10.dp),
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
}