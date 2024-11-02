package com.kyang.timetracker.ui.component

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kyang.timetracker.navigation.BottomAppBarItem
import kotlinx.coroutines.selects.select

@Composable
fun NavigationBottomBar(modifier: Modifier = Modifier, items: List<BottomAppBarItem>) {

    NavigationBar(modifier) {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.selected,
                icon = item.icon,
                onClick = item.onClick,
                label = item.label
            )
        }
    }
}