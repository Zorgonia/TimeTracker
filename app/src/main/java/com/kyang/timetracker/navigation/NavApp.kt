package com.kyang.timetracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kyang.history.navigation.historyScreen
import com.kyang.home.navigation.HomeRoute
import com.kyang.home.navigation.homeScreen

@Composable
fun NavApp(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier
    ) {
       homeScreen()
       historyScreen()
    }

}