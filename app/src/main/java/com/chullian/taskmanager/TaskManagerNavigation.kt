package com.chullian.taskmanager

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Created by binMammootty on 22/03/2022.
 */


object TaskManagerDestinations {
    const val HOME_ROUTE = "home"
}

/**
 * Models the navigation actions in the app.
 */
class TaskManagerNavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(TaskManagerDestinations.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}