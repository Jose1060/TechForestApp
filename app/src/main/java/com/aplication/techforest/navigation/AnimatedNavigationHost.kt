package com.aplication.techforest.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.view.presentation.login.LoginScreen
import com.aplication.techforest.view.presentation.screens.HomeScreen
import com.aplication.techforest.view.presentation.screens.MainScreen
import com.aplication.techforest.viewmodel.LoginViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun AnimatedNavigationHost(
    navController: NavHostController
) {
    BoxWithConstraints {
        AnimatedNavHost(
            navController = navController,
            startDestination = Destinations.Login.route
        ) {
            addLogin(navController)
            addHomeScreen()
            addHome()
        }
    }
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addLogin(
    navController: NavHostController
) {
    composable(
        route = Destinations.Login.route,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        },
        exitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { -1000 },
                animationSpec = tween(500)
            )
        },
        popEnterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { -1000 },
                animationSpec = tween(500)
            )
        },
        popExitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        }
    ) {
        val viewModel: LoginViewModel = hiltViewModel()
        val userId = viewModel.state.value.userId

        Log.d("NavG.addLogin", "$userId")

        if (viewModel.state.value.successLogin) {
            LaunchedEffect(key1 = Unit) {
                //navController.navigate(
                //    Destinations.HomeScreen.createRoute(userId = userId)
                //)
                navController.navigate(
                    Destinations.MainScreen.route + "/$userId"
                ) {
                    popUpTo(Destinations.Login.route) {
                        inclusive = true
                    }
                }
            }
        } else {
            LoginScreen(
                state = viewModel.state.value,
                onLogin = viewModel::login,
                onDismissDialog = viewModel::hideErrorDialog
            )
        }
    }
}

@ExperimentalCoilApi
@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addHome() {
    composable(
        route = Destinations.MainScreen.route + "/{userId}",
        arguments = Destinations.MainScreen.arguments
    ) { backStackEntry ->
        val userId = backStackEntry.arguments?.getInt("userId")
        Log.d("NavGraphBuilder.addHome", "$userId")
        requireNotNull(userId, { "UserID not null" })

        MainScreen(userId = userId)
    }
}

@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addHomeScreen() {
    composable(
        route = Destinations.HomeScreen.route,
        arguments = Destinations.HomeScreen.arguments
    ) { backStackEntry ->
        val userId = backStackEntry.arguments?.getInt("userId")
        Log.d("Prueba 1:", "$userId")
        requireNotNull(userId, { "UserID not null" })
        HomeScreen(userId = userId)
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.addDevices() {
    composable(
        route = Destinations.Devices.route
    ) {

    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.addPlants() {
    composable(
        route = Destinations.Plants.route
    ) {

    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.addProfile() {
    composable(
        route = Destinations.Profile.route
    ) {

    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.addSettings() {
    composable(
        route = Destinations.Settings.route
    ) {

    }
}


