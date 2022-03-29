package com.chullian.taskmanager

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chullian.taskmanager.screens.home.HomeViewModel
import com.chullian.taskmanager.screens.home.HomeRoute

/**
 * Created by binMammootty on 22/03/2022.
 */


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskManagerNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TaskManagerDestinations.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(TaskManagerDestinations.HOME_ROUTE) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeRoute(
                homeViewModel = homeViewModel
            )
        }
    }
}