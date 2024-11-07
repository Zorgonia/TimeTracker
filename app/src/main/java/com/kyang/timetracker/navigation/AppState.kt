package com.kyang.timetracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.kyang.timetracker.history.navigation.navigateToHistory
import com.kyang.timetracker.home.navigation.navigateToHome

/**
 * App State to control the nav controller and navigation in the app
 */
class AppState(
    private val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    /**
     * Navigation function for top level destinations
     * @param topLevelDestination: The destination to navigate to
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val navOptions = navOptions {
            // save current state in backstack
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // only launch one instance of destination
            launchSingleTop = true
            // restore previous state if it exists
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