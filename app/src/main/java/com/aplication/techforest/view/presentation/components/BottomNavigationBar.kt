package com.aplication.techforest.view.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aplication.techforest.navigation.Destinations
import com.aplication.techforest.ui.theme.*


@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<Destinations>,
    userId: Int,
) {
    val currentRoute = currentRoute(navController)

    BottomNavigation(
        backgroundColor = RichBlack,
        elevation = 5.dp,

        ) {
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.iconId),
                        contentDescription = screen.title,
                        modifier = Modifier.size(20.dp),
                    )
                },
                label = { Text(text = screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                    }
                },
                selectedContentColor = Turquoise,
                alwaysShowLabel = false
            )
        }
    }
}

@Composable
fun BottomNavigationBar2(
    navController: NavHostController,
    itemHome: Destinations.HomeScreen = Destinations.HomeScreen,
    itemDevices: Destinations.Devices = Destinations.Devices,
    itemPlants: Destinations.Plants = Destinations.Plants,
    itemProfile: Destinations.Profile = Destinations.Profile,
    itemSettings: Destinations.Settings = Destinations.Settings,
    itemAdvertisement: Destinations.Advertisements = Destinations.Advertisements,

    userId: Int,
) {
    val currentRoute = currentRoute(navController)

    BottomNavigation(
        backgroundColor = RichBlack,
        elevation = 5.dp,

        ) {

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = itemAdvertisement.iconId),
                    contentDescription = itemAdvertisement.title,
                    modifier = Modifier.size(20.dp),
                )
            },
            label = { Text(text = itemAdvertisement.title) },
            selected = currentRoute == itemAdvertisement.route,
            onClick = {
                navController.navigate(itemAdvertisement.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }

                    launchSingleTop = true
                }
            },
            selectedContentColor = Turquoise,
            alwaysShowLabel = false
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = itemHome.iconId),
                    contentDescription = itemHome.title,
                    modifier = Modifier.size(20.dp),
                )
            },
            label = { Text(text = itemHome.title) },
            selected = currentRoute == itemHome.route,
            onClick = {
                navController.navigate(itemHome.createRoute(userId = userId)) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }

                    launchSingleTop = true
                }
            },
            selectedContentColor = Turquoise,
            alwaysShowLabel = false
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = itemDevices.iconId),
                    contentDescription = itemDevices.title,
                    modifier = Modifier.size(20.dp),
                )
            },
            label = { Text(text = itemDevices.title) },
            selected = currentRoute == itemDevices.route,
            onClick = {
                navController.navigate(itemDevices.createRoute(userId = userId)) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }

                    launchSingleTop = true
                }
            },
            selectedContentColor = Turquoise,
            alwaysShowLabel = false
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = itemPlants.iconId),
                    contentDescription = itemPlants.title,
                    modifier = Modifier.size(20.dp),
                )
            },
            label = { Text(text = itemPlants.title) },
            selected = currentRoute == itemPlants.route,
            onClick = {
                navController.navigate(itemPlants.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }

                    launchSingleTop = true
                }
            },
            selectedContentColor = Turquoise,
            alwaysShowLabel = false
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = itemProfile.iconId),
                    contentDescription = itemProfile.title,
                    modifier = Modifier.size(20.dp),
                )
            },
            label = { Text(text = itemProfile.title) },
            selected = currentRoute == itemProfile.route,
            onClick = {
                navController.navigate(itemProfile.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                }
            },
            selectedContentColor = Turquoise,
            alwaysShowLabel = false
        )
    }
}


@Composable
private fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}