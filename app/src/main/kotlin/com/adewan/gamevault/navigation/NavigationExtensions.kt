package com.adewan.gamevault.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.currentBackStackEntryAsState
import com.adewan.gamevault.features.ListType

fun NavOptionsBuilder.navigateBottomBar(navController: NavHostController) {
  popUpTo(navController.graph.findStartDestination().id) { saveState = true }
  launchSingleTop = true
  restoreState = true
}

fun NavHostController.navigateToLeafScreen(route: String) {
  navigate(LEAF_SCREEN_PREFIX + route)
}

fun NavHostController.navigateToGameListView(listType: ListType) {
  val uri =
    Uri.Builder()
      .path(GAME_LIST_BASE)
      .appendQueryParameter("type", listType.name)
      .build()
      .toString()
  navigateToLeafScreen(uri)
}

@Composable
fun hideBottomBarEffect(navController: NavHostController): Boolean {
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination
  return currentDestination?.route?.startsWith(LEAF_SCREEN_PREFIX) ?: false
}
