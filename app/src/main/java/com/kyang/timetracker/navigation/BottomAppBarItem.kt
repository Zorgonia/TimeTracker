package com.kyang.timetracker.navigation

import androidx.compose.runtime.Composable

data class BottomAppBarItem(
    val selected: Boolean,
    val onClick: () -> Unit,
    val label: @Composable () -> Unit,
    val icon: @Composable () -> Unit,
)