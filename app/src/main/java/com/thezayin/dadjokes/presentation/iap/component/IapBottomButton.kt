package com.thezayin.dadjokes.presentation.iap.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.core.R

@Composable
fun IapBottomButton(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.iap_button_color),
                contentColor = colorResource(id = R.color.white)
            ),
            shape = RoundedCornerShape(18.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Continue",
                color = colorResource(id = R.color.white),
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_shield),
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .size(20.dp)
            )
            Text(
                text = "No payment now",
                fontSize = 14.sp,
                color = colorResource(id = R.color.black)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Terms & Conditions",
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_color),
                textDecoration = TextDecoration.Underline
            )
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .height(20.dp)
                    .width(0.5.dp)
                    .background(color = colorResource(id = R.color.text_color))
            )
            Text(
                text = "Privacy Policy",
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_color),
                textDecoration = TextDecoration.Underline
            )
        }
    }
}