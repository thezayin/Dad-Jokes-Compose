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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.presentation.utils.Constants.ABOUT_US_URL
import com.thezayin.dadjokes.presentation.utils.openLink
import com.thezayin.dadjokes.presentation.utils.sendMail

@Composable
fun OtherListComponent(modifier: Modifier, navigator: DestinationsNavigator) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Button(
            onClick = { /*TODO*/ },
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 35.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.green_level_1)
            )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_crown),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                )
                Text(
                    text = "Upgrade to Premium",
                    modifier = Modifier.padding(start = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.white)
                )
            }
        }
        Text(
            text = "Other",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp, top = 40.dp),
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
                        .padding(vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(25.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.green_level_1),
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
                                painter = painterResource(id = R.drawable.ic_star),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(15.dp),
                                alignment = Alignment.BottomCenter
                            )
                        }
                    }
                    Text(
                        text = "Leave a rating / review",
                        modifier = Modifier.padding(start = 40.dp)
                    )
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
                        .padding(vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(25.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.green_level_2),
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
                                painter = painterResource(id = R.drawable.ic_app),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(15.dp),
                                alignment = Alignment.BottomCenter
                            )
                        }
                    }
                    Text(text = "More Apps", modifier = Modifier.padding(start = 40.dp))
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
                            context.openLink(ABOUT_US_URL)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(25.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.orange_level_1),
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
                                painter = painterResource(id = R.drawable.ic_about),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(15.dp),
                                alignment = Alignment.BottomCenter
                            )
                        }
                    }
                    Text(text = "About Us", modifier = Modifier.padding(start = 40.dp))
                }
            }
        }
    }
}