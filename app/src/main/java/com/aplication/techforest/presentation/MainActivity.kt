package com.aplication.techforest.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.navigation.AnimatedNavigationHost
import com.aplication.techforest.navigation.Destinations
import com.aplication.techforest.navigation.NavigationHost
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import com.aplication.techforest.presentation.components.BottomNavigationBar2

import com.aplication.techforest.presentation.login.LoginScreen
import com.aplication.techforest.viewmodel.LoginViewModel

import com.aplication.techforest.ui.theme.TechForestTheme
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
                    AnimatedNavigationHost(navController)
                }
            }
        }
    }
}







