package com.thezayin.dadjokes.presentation.savedjokes.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.dadjokes.R

@Composable
fun SavedScreenTopBar(
    navigator: DestinationsNavigator
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.background))
            .padding(top = 20.dp).padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
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
            modifier = Modifier.padding(start = 30.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black)
        )
    }
}