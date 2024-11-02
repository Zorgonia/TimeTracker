package com.kyang.timetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.kyang.timetracker.navigation.AppState
import com.kyang.timetracker.navigation.BottomAppBarItem
import com.kyang.timetracker.navigation.NavApp
import com.kyang.timetracker.ui.component.NavigationBottomBar
import com.kyang.timetracker.ui.theme.TimeTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimeTrackerTheme {
                TimeTrackerApp()
            }
        }
    }
}


@Composable
fun TimeTrackerApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val appState = remember { AppState(navController) }

    Scaffold(bottomBar = {
        NavigationBottomBar(items = appState.topLevelDestinations.map { dest ->
            val selected = appState.currentDestination == dest.route
            BottomAppBarItem(selected = selected, onClick = {
                appState.navigateToTopLevelDestination(dest)
            }, icon = {
                Icon(
                    dest.icon,
                    contentDescription = stringResource(dest.iconDescription),
                    tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            }, label = {
                Text(stringResource(dest.label))
            })
        })
    }) { innerPadding ->
        NavApp(modifier = Modifier.padding(innerPadding), navController)
    }
}
