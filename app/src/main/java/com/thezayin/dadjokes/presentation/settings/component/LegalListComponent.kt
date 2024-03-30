package com.thezayin.dadjokes.presentation.settings.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.presentation.utils.Constants.PRIVATE_POLICY_URL
import com.thezayin.dadjokes.presentation.utils.Constants.TERMS_CONDITIONS_URL
import com.thezayin.dadjokes.presentation.utils.openLink
import com.thezayin.dadjokes.presentation.utils.sendMail

@Composable
fun LegalListComponent(modifier: Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Text(
            text = "Legal",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            fontSize = 22.sp,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.Bold
        )
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                colorResource(id = R.color.grey_level_5)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable {
                            context.openLink(PRIVATE_POLICY_URL)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(25.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.brown_level_1),
                            contentColor = colorResource(id = R.color.white)
                        ),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_privacy),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(15.dp),
                                alignment = Alignment.BottomCenter
                            )
                        }
                    }
                    Text(text = "Privacy Policy", modifier = Modifier.padding(start = 40.dp))
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = colorResource(id = R.color.white))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable {
                            context.openLink(TERMS_CONDITIONS_URL)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(25.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.yellow_level_1),
                            contentColor = colorResource(id = R.color.white)
                        ),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_terms),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(15.dp),
                                alignment = Alignment.BottomCenter
                            )
                        }
                    }
                    Text(text = "Terms & Conditions", modifier = Modifier.padding(start = 40.dp))
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = colorResource(id = R.color.white))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable {
                            context.sendMail()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(25.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.blue_level_1),
                            contentColor = colorResource(id = R.color.white)
                        ),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_mail),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(15.dp),
                                alignment = Alignment.BottomCenter
                            )
                        }
                    }
                    Text(text = "Contact Us", modifier = Modifier.padding(start = 40.dp))
                }
            }
        }
    }
}