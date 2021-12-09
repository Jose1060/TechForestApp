package com.aplication.techforest.view.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.ExperimentalMaterialApi
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.navigation.AnimatedNavigationHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

import com.aplication.techforest.ui.theme.TechForestTheme
import com.aplication.techforest.view.presentation.screens.PlantsScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@ExperimentalFoundationApi
@AndroidEntryPoint
@ExperimentalAnimationApi
@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechForestTheme {
                val navController = rememberAnimatedNavController()
                BoxWithConstraints {
                    PlantsScreen()
                    //AnimatedNavigationHost(navController)
                }
            }
        }
    }
}







