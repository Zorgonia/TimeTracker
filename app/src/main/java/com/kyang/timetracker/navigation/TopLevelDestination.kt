package com.kyang.timetracker.navigation

import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.kyang.history.navigation.HistoryRoute
import com.kyang.home.navigation.HomeRoute
import com.kyang.timetracker.R
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val route: KClass<*>,
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val iconDescription: Int,
) {
    HOME(
        route = HomeRoute::class,
        icon = Icons.Default.Home,
        label = R.string.nav_bar_home_label,
        iconDescription = R.string.nav_home_icon
    ),
    HISTORY(
        route = HistoryRoute::class,
        icon = Icons.Default.Info,
        label = R.string.nav_bar_history_label,
        iconDescription = R.string.nav_history_icon
    )
}