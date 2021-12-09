package com.aplication.techforest.view.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter

@Preview
@Composable
fun AdvertisementsScreen() {
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = rememberImagePainter(
                data = "https://i.pinimg.com/originals/02/7d/82/027d8214653f75a3be25a00d46a03cd5.png",
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}