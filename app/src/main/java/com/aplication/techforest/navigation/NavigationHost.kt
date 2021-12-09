package com.aplication.techforest.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.navigation.Destinations.*

import com.aplication.techforest.view.presentation.screens.*
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
            Devices(userId = userId, navController = navController)
        }

        composable(Plants.route) {
            PlantsScreen()
        }

        composable(Profile.route) {
            Profiles()
        }

        composable(Settings.route) {
            Settings()
        }

        composable(Advertisements.route) {
            AdvertisementsScreen()
        }

        composable(
            route = DeviceDetail.route,
            arguments = DeviceDetail.arguments
        ) { navBackStackEntry ->
            val deviceId = navBackStackEntry.arguments?.getInt("deviceId")
            requireNotNull(deviceId)
            Log.d("DeviceDetailID", "$deviceId")
            DeviceDetailScreen(deviceId = deviceId, navController = navController)
        }

        composable(
            route = OptionsDevice.route,
            arguments = OptionsDevice.arguments
        ){ navBackStackEntry ->
            val optionsId = navBackStackEntry.arguments?.getInt("optionsId")
            requireNotNull(optionsId)
            Log.d("OptionsID", "$optionsId")
            EditOptionsScreen(optionsId = optionsId)
        }

    }


}


