package com.adewan.gamevault.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.adewan.gamevault.features.GameListView

const val LEAF_SCREEN_PREFIX = "leaf/"

@Composable
fun GVNavHost(
  modifier: Modifier = Modifier,
  navController: NavHostController,
  startDestination: String,
) {
  NavHost(modifier = modifier, navController = navController, startDestination = startDestination) {
    defaultBottomNavigationDestinations.forEach { dest ->
      composable(route = dest.route) { dest.renderer(navController) }
    }
    leafComposable(route = GAME_LIST_ROUTE) {
      GameListView(onBack = { navController.popBackStack() })
    }
  }
}

fun NavGraphBuilder.leafComposable(
  route: String,
  content: @Composable (NavBackStackEntry) -> Unit,
) {
  composable(
    route = LEAF_SCREEN_PREFIX + route,
    enterTransition = {
      slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Start)
    },
    exitTransition = {
      slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.End)
    },
  ) {
    content(it)
  }
}
