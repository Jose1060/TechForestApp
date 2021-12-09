package com.aplication.techforest.view.presentation.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.aplication.techforest.navigation.Destinations
import com.aplication.techforest.view.presentation.screens.Camera.CameraView

@Composable
fun PlantsScreen(navController: NavHostController) {
    CameraView(onImageCaptured = { uri, fromGallery ->
        Log.d("TAG", "Image Uri Captured from Camera View $uri, $fromGallery")
        navController.navigate(Destinations.ViewPlantCamera.createRoute(uri.toString()))

    }, onError = { imageCaptureException ->

    })
}