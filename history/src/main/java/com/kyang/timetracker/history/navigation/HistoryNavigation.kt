package com.kyang.timetracker.history.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kyang.timetracker.history.ui.HistoryScreen
import com.kyang.timetracker.history.ui.HistoryViewModel
import kotlinx.serialization.Serializable

@Serializable
object HistoryRoute

fun NavController.navigateToHistory(navOptions: NavOptions) = navigate(route = HistoryRoute, navOptions)

fun NavGraphBuilder.historyScreen() {
    composable<HistoryRoute>() {
        val viewModel = hiltViewModel<HistoryViewModel>()
        viewModel.getHistoryItems()
        HistoryScreen(viewModel = viewModel)
    }
}