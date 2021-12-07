package com.aplication.techforest.presentation.screens

import android.Manifest
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File

@ExperimentalPermissionsApi
@Composable
fun Plants() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        val cameraPermissionState = rememberPermissionState(
            permission = Manifest.permission.CAMERA)

        Button(
            onClick = {
                cameraPermissionState.launchPermissionRequest()
            }
        ) {
            Text(text = "Permission")
        }

        Spacer(modifier = Modifier.height(30.dp))

        //PlantCameraOpen(getDirectory())
    }


}
/*
//Store the capture image
private fun getDirectory(): File {
    val mediaDir = externalMediaDirs.firstOrNull()?.let {
        File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
    }
    return if (mediaDir != null && mediaDir.exists())
        mediaDir else filesDir
}
*/