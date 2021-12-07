package com.aplication.techforest.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation


@Composable
fun AdvertisementsScreen() {
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = rememberImagePainter(
                data = "https://images.pexels.com/photos/9604597/pexels-photo-9604597.jpeg",
            ),
            contentDescription = null,
        )
    }
}