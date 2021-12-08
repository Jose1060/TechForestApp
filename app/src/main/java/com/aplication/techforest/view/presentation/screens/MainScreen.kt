package com.aplication.techforest.view.presentation.screens

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.navigation.NavigationHost
import com.aplication.techforest.view.presentation.components.BottomNavigationBar2
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalAnimationApi
@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(userId: Int) {
    val userId = userId
    Log.d("MainScreen", "$userId")
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar2(
                navController = navController,
                userId = userId
            )
        }
    ) {
        NavigationHost(navController = navController, userId = userId)
    }
}