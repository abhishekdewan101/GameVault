package com.adewan.gamevault.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

fun NavOptionsBuilder.navigateBottomBar(navController: NavHostController) {
  popUpTo(navController.graph.findStartDestination().id) { saveState = true }
  launchSingleTop = true
  restoreState = true
}
