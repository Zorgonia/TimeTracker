package com.kyang.timetracker.home.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kyang.timetracker.home.ui.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(route = HomeRoute, navOptions)

/**
 * Nav graph for the home screen. Any inner screens would be defined here.
 */
fun NavGraphBuilder.homeScreen() {
    composable<HomeRoute>() {
        HomeScreen(viewModel = hiltViewModel())
    }
}