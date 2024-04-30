package com.thezayin.dadjokes.presentation.savedjokes.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R
import com.thezayin.dadjokes.presentation.savedjokes.SaveViewModel

@Composable
fun DeleteComponent(
    saveViewModel: SaveViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.background))
            .padding(bottom = 5.dp)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "Delete all",
            fontWeight = FontWeight.Light,
            color = colorResource(id = R.color.text_color),
            fontSize = 10.sp,
            fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                saveViewModel.deleteAllJokes()
            }
        )
    }
}