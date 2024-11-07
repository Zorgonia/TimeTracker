package com.kyang.timetracker.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.kyang.timetracker.history.navigation.HistoryRoute
import com.kyang.timetracker.home.navigation.HomeRoute
import com.kyang.timetracker.R
import kotlin.reflect.KClass

/**
 * Enum class for the top level destinations in the app
 * @param route The navigation route for the destination
 * @param label The string resource for the label for the destination
 * @param icon The icon for the destination
 * @param iconDescription The string resource for the description of the icon for the destination
 */
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