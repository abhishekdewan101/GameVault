package com.adewan.gamevault.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.currentBackStackEntryAsState

fun NavOptionsBuilder.navigateBottomBar(navController: NavHostController) {
  popUpTo(navController.graph.findStartDestination().id) { saveState = true }
  launchSingleTop = true
  restoreState = true
}

fun NavHostController.navigateToLeafScreen(route: String) {
  navigate(LEAF_SCREEN_PREFIX + route)
}

@Composable
fun hideBottomBarEffect(navController: NavHostController): Boolean {
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination
  return currentDestination?.route?.startsWith(LEAF_SCREEN_PREFIX) ?: false
}
