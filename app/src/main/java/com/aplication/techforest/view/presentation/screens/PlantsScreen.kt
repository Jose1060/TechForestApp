package com.aplication.techforest.view.presentation.screens

import android.util.Log
import androidx.compose.runtime.Composable
import com.aplication.techforest.view.presentation.screens.Camera.CameraView

@Composable
fun PlantsScreen() {
    CameraView(onImageCaptured = { uri, fromGallery ->
        Log.d("TAG", "Image Uri Captured from Camera View $uri, $fromGallery")

    }, onError = { imageCaptureException ->

    })
}