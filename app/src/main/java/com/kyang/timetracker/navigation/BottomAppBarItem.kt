package com.kyang.timetracker.navigation

import androidx.compose.runtime.Composable

/**
 * Data class for bottom app bar item
 * @param selected Whether the item is selected
 * @param onClick The action to perform when the item is clicked
 * @param label The label to display for the item
 * @param icon The icon to display for the item
 */
data class BottomAppBarItem(
    val selected: Boolean,
    val onClick: () -> Unit,
    val label: @Composable () -> Unit,
    val icon: @Composable () -> Unit,
)