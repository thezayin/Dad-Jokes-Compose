package com.thezayin.dadjokes.screens.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.core.analytics.analytics.Analytics
import com.thezayin.dadjokes.core.analytics.events.AnalyticsEvent
import com.thezayin.dadjokes.core.framework.extension.functions.openLink
import com.thezayin.dadjokes.core.framework.utils.Constants.ABOUT_US_URL
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp


@Composable
fun OtherListComponent(analytics: Analytics) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.sdp, horizontal = 10.sdp)
    ) {
        Text(
            text = stringResource(id = R.string.others),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.sdp),
            fontSize = 10.ssp,
            fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
            color = colorResource(id = R.color.black),
        )

        Card(
            shape = RoundedCornerShape(10.sdp), colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.card_background)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp, vertical = 12.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Sharp.Star,
                    tint = colorResource(id = R.color.black),
                    contentDescription = "",
                    modifier = Modifier.size(14.sdp),
                )
                Text(
                    text = stringResource(id = R.string.leave_rating_review),
                    color = colorResource(id = R.color.black),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 8.ssp,
                    modifier = Modifier.padding(start = 20.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.sdp)
        )

        Card(
            shape = RoundedCornerShape(10.sdp), colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.card_background)
            ), onClick = {
                analytics.logEvent(AnalyticsEvent.SettingsContactUs("clicked"))
                context.openLink(ABOUT_US_URL)
            }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp, vertical = 12.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    tint = colorResource(id = R.color.black),
                    imageVector = Icons.Sharp.AccountCircle,
                    contentDescription = "",
                    modifier = Modifier.size(12.sdp),
                )
                Text(
                    text = stringResource(id = R.string.about_us),
                    color = colorResource(id = R.color.black),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 8.ssp,
                    modifier = Modifier.padding(start = 20.sdp)
                )
            }
        }
    }
}
