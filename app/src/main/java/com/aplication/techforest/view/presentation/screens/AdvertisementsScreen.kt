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
                data = "https://images.pexels.com/photos/9604597/pexels-photo-9604597.jpeg",
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}