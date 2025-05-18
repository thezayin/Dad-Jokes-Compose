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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.core.analytics.analytics.Analytics
import com.thezayin.dadjokes.core.analytics.events.AnalyticsEvent
import com.thezayin.dadjokes.core.framework.extension.functions.openLink
import com.thezayin.dadjokes.core.framework.extension.functions.sendMail
import com.thezayin.dadjokes.core.framework.utils.Constants.PRIVATE_POLICY_URL
import com.thezayin.dadjokes.core.framework.utils.Constants.TERMS_CONDITIONS_URL
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun LegalListComponent(analytics: Analytics) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.sdp, horizontal = 10.sdp)
    ) {
        Text(
            text = stringResource(id = R.string.legal),
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
            ), onClick = {
                analytics.logEvent(AnalyticsEvent.SettingsPrivacyPolicy("clicked"))
                context.openLink(PRIVATE_POLICY_URL)
            }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp, vertical = 12.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Sharp.Info,
                    tint = colorResource(id = R.color.black),
                    contentDescription = stringResource(id = R.string.privacy_policy_icon),
                    modifier = Modifier.size(12.sdp),
                )
                Text(
                    text = stringResource(id = R.string.privacy_policy),
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
                // Track event when Terms & Conditions is clicked
                analytics.logEvent(AnalyticsEvent.SettingsTermsConditions("clicked"))
                context.openLink(TERMS_CONDITIONS_URL)
            }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp, vertical = 12.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    tint = colorResource(id = R.color.black),
                    painter = painterResource(id = R.drawable.ic_terms),
                    contentDescription = stringResource(id = R.string.terms_conditions_icon),
                    modifier = Modifier.size(12.sdp),
                )
                Text(
                    text = stringResource(id = R.string.terms_conditions),
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
                context.sendMail()
            }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp, vertical = 12.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    tint = colorResource(id = R.color.black),
                    contentDescription = stringResource(id = R.string.contact_us_icon),
                    modifier = Modifier.size(12.sdp),
                )
                Text(
                    text = stringResource(id = R.string.contact_us),
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
    }
}