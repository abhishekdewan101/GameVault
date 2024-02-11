package com.adewan.gamevault.navigation

import androidx.compose.material3.Text
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
    composable("home") { Text("Home") }
  }
}
