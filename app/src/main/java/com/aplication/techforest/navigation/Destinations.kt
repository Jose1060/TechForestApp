package com.aplication.techforest.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

import androidx.navigation.NavType


import com.aplication.techforest.R

sealed class Destinations(
    val route: String,
    val title: String,
    @DrawableRes val iconId: Int,
    val arguments: List<NamedNavArgument>
) {
    object Advertisements :
        Destinations("advertisement", "Adds", R.drawable.ic_baseline_advertisements, emptyList())

    object Login : Destinations("login", "Login", R.drawable.ic_baseline_circle_24, emptyList())

    object MainScreen : Destinations("main", "Main", R.drawable.ic_baseline_circle_24,
        listOf(
            navArgument("userId") { type = NavType.IntType }
        )
    )

    object HomeScreen : Destinations(
        "homeScreen/{userId}", "Home", R.drawable.ic_home_2,
        listOf(
            navArgument("userId") { type = NavType.IntType }
        )
    ) {
        fun createRoute(userId: Int) = "homeScreen/$userId"
    }

    object Devices : Destinations(
        "devices/{userId}", "Devices", R.drawable.ic_lambda,
        listOf(navArgument("userId") { type = NavType.IntType })
    ) {
        fun createRoute(userId: Int) = "Devices/$userId"
    }

    object Plants :
        Destinations("plants", "Plants", R.drawable.ic_baseline_local_florist_24, emptyList())

    object Profile : Destinations("profile", "Profile", R.drawable.ic_profile, emptyList())

    object Settings :
        Destinations("settings", "Settings", R.drawable.ic_baseline_settings_24, emptyList())

    object DeviceDetail : Destinations(
        "deviceDetail/{deviceId}", "Detail Device", R.drawable.ic_baseline_circle_24,
        listOf(navArgument("deviceId") { type = NavType.IntType })
    ) {
        fun createRoute(deviceId: Int) = "deviceDetail/$deviceId"
    }

    object OptionsDevice : Destinations(
        "optionsDevice/{optionsId}", "Options Device", R.drawable.ic_baseline_circle_24,
        listOf(navArgument("optionsId") { type = NavType.IntType })
    ) {
        fun createRoute(optionsId: Int) = "optionsDevice/$optionsId"
    }
}

sealed class Destinations1(
    val route: String
) {
    object LoginScreen : Destinations1("Login")
    object HomeScreen : Destinations1("Home")
}


sealed class Destinations3(
    val route: String,
    val arguments: List<NamedNavArgument>
) {

    object Login : Destinations3("login", emptyList())

    object Home : Destinations3(
        "home/",
        listOf(
            navArgument("userId") { type = NavType.IntType },
        )
    )


}
