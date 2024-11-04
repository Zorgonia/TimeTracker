package com.kyang.timetracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.kyang.timetracker.history.navigation.navigateToHistory
import com.kyang.timetracker.home.navigation.navigateToHome

class AppState(
    private val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> {
                navController.navigateToHome(navOptions)
            }
            TopLevelDestination.HISTORY -> {
                navController.navigateToHistory(navOptions)
            }
        }
    }
}