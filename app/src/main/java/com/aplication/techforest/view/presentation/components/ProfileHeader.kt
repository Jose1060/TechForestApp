package com.aplication.techforest.view.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.aplication.techforest.R
import com.aplication.techforest.model.User.UserResponse
import com.aplication.techforest.ui.theme.DarkStateGray


@ExperimentalCoilApi
@Composable
fun ProfileHeader(user: UserResponse) {
    Surface(
        color = DarkStateGray,
        contentColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = rememberImagePainter(data = "https://images.pexels.com/photos/9604597/pexels-photo-9604597.jpeg",
                    builder = {
                        transformations(CircleCropTransformation())
                    }),
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .clip(shape = CircleShape)
                    .border(
                        border = BorderStroke(1.dp, color = Color.White),
                        shape = CircleShape
                    )
            )
            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = "${user.nombres} ${user.apellidos}",
                style = MaterialTheme.typography.h6,
                color = Color.White
            )

            Text(
                text = user.correo,
                style = MaterialTheme.typography.body2,
                color = Color.White
            )

            Spacer(modifier = Modifier.padding(top = 2.dp))
            Text(text = "Premiun", style = MaterialTheme.typography.caption)
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_monetization_on_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    tint = Color.White
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_lambda),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Plan()
        }
    }

}

@Composable
fun Plan() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Plan", style = MaterialTheme.typography.caption)
            Text(text = "Initial", style = MaterialTheme.typography.h5)
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "Hours", style = MaterialTheme.typography.caption)
            Text(text = "30", style = MaterialTheme.typography.h5)
        }

    }
}

