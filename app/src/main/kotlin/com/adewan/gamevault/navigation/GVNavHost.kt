package com.adewan.gamevault.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun GVNavHost(
  modifier: Modifier = Modifier,
  navController: NavHostController,
  startDestination: String,
) {
  NavHost(modifier = modifier, navController = navController, startDestination = startDestination) {
    defaultNavigationDestinations.forEach { dest ->
      composable(route = dest.route) { dest.renderer() }
    }
  }
}
