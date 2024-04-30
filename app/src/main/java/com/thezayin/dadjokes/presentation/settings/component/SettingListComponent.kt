package com.thezayin.dadjokes.presentation.settings.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.core.R
import com.thezayin.core.utils.Constants
import com.thezayin.core.utils.Constants.ABOUT_US_URL
import com.thezayin.core.utils.openLink
import com.thezayin.core.utils.sendMail

@Composable
fun SettingListComponent(modifier: Modifier, navigator: DestinationsNavigator) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp),
                alignment = Alignment.BottomCenter
            )
            Text(
                text = "Leave a rating / review",
                color = colorResource(id = R.color.text_color),
                modifier = Modifier.padding(start = 20.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_app),
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp),
                alignment = Alignment.BottomCenter
            )
            Text(
                text = "More Apps",
                color = colorResource(id = R.color.text_color),
                modifier = Modifier.padding(start = 20.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .clickable {
                    context.openLink(ABOUT_US_URL)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_about),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp),
                alignment = Alignment.BottomCenter
            )
            Text(
                text = "About Us",
                color = colorResource(id = R.color.text_color),
                modifier = Modifier.padding(start = 20.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .clickable {
                    context.openLink(Constants.PRIVATE_POLICY_URL)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_privacy),
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp),
                alignment = Alignment.BottomCenter
            )
            Text(
                text = "Privacy Policy",
                color = colorResource(id = R.color.text_color),
                modifier = Modifier.padding(start = 20.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .clickable {
                    context.openLink(Constants.TERMS_CONDITIONS_URL)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_terms),
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp),
                alignment = Alignment.BottomCenter
            )

            Text(
                text = "Terms & Conditions",
                color = colorResource(id = R.color.text_color),
                modifier = Modifier.padding(start = 20.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .clickable {
                    context.sendMail()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_mail),
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp),
                alignment = Alignment.BottomCenter
            )

            Text(
                text = "Contact Us",
                color = colorResource(id = R.color.text_color),
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}

