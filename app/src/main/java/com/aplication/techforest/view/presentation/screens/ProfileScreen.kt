package com.aplication.techforest.view.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.view.presentation.components.AppBar
import com.aplication.techforest.view.presentation.components.ProfileHeader

@ExperimentalCoilApi
@Composable
fun Profiles() {
    Column {
        AppBar()
    }
    ProfileHeader()
}