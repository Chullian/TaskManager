package com.chullian.taskmanager

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chullian.taskmanager.ui.theme.TaskManagerTheme
import com.chullian.taskmanager.utils.WindowSize
import com.google.accompanist.insets.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun TaskManagerApp(
    windowSize: WindowSize
) {
    TaskManagerTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            val darkIcons = MaterialTheme.colors.isLight


            val navController = rememberNavController()
            val navigationActions = remember(navController) {
                TaskManagerNavigationActions(navController)
            }

            val coroutineScope = rememberCoroutineScope()

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route ?: TaskManagerDestinations.HOME_ROUTE

            val isExpandedScreen = false//windowSize == WindowSize.Expanded
            val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)
            Row(
                Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding(bottom = false)
            ){
                TaskManagerNavGraph(
                    navController = navController,
                )
            }
        }
    }
}

/**
 * Determine the drawer state to pass to the modal drawer.
 */
@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    return if (!isExpandedScreen) {
        // If we want to allow showing the drawer, we use a real, remembered drawer
        // state defined above
        drawerState
    } else {
        // If we don't want to allow the drawer to be shown, we provide a drawer state
        // that is locked closed. This is intentionally not remembered, because we
        // don't want to keep track of any changes and always keep it closed
        DrawerState(DrawerValue.Closed)
    }
}

/**
 * Determine the content padding to apply to the different screens of the app
 */
@Composable
fun rememberContentPaddingForScreen(additionalTop: Dp = 0.dp) =
    rememberInsetsPaddingValues(
        insets = LocalWindowInsets.current.systemBars,
        applyTop = false,
        applyEnd = false,
        applyStart = false,
        additionalTop = additionalTop
    )