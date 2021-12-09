package com.aplication.techforest.view.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aplication.techforest.ui.theme.CadetBlue
import com.aplication.techforest.R
import com.aplication.techforest.ui.theme.DarkStateGray

@Preview
@Composable
fun AppBar() {
    Surface(color = CadetBlue) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = DarkStateGray
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back_ios_24),
                    contentDescription = null
                )
                OutlinedButton(
                    onClick = {},
                    Modifier
                        .background(Color.Transparent),
                    border = BorderStroke(width = 1.dp, color = Color.White)
                ) {
                    Text(text = "Logout")

                }

            }

        }

    }
}