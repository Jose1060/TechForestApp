package com.aplication.techforest.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.navigation.Destinations.*

import com.aplication.techforest.presentation.login.LoginScreen
import com.aplication.techforest.presentation.screens.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalAnimationApi
@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun NavigationHost(
    navController: NavHostController,
    userId: Int
) {
    Log.d("NavController", "$userId")

    NavHost(navController = navController, startDestination = Advertisements.route) {
        composable(
            route = HomeScreen.route,
            arguments = HomeScreen.arguments
        ) { navBackStackEntry ->
            val userId = navBackStackEntry.arguments?.getInt("userId")
            requireNotNull(userId)
            Log.d("NavHomeScreen", "$userId")
            HomeScreen(userId = userId)
        }

        composable(
            route = Devices.route,
            arguments = Devices.arguments
        ) { navBackStackEntry ->
            val userId = navBackStackEntry.arguments?.getInt("userId")
            requireNotNull(userId)
            Log.d("NavHomeScreen", "$userId")
            Devices(userId =userId)
        }

        composable(Plants.route) {
            Plants()
        }

        composable(Profile.route) {
            Profiles()
        }

        composable(Settings.route) {
            Settings()
        }

        composable(Advertisements.route){
            AdvertisementsScreen()
        }

    }


}


