package com.adewan.gamevault.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.adewan.gamevault.features.GameListView
import com.adewan.gamevault.features.GameListViewModel
import com.adewan.gamevault.features.ListType

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
    leafComposable(route = GAME_LIST_ROUTE, arguments = listOf(navArgument("type") {})) {
      val args = it.arguments ?: throw IllegalStateException()
      val typeValue = args.getString("type") ?: throw IllegalStateException()
      val type = ListType.valueOf(typeValue)
      val viewModel: GameListViewModel = hiltViewModel()
      LaunchedEffect(key1 = viewModel) { viewModel.loadGames(type) }
      GameListView(
        title = type.title,
        games = viewModel.loadGames(type),
        onBack = { navController.popBackStack() },
      )
    }
  }
}

fun NavGraphBuilder.leafComposable(
  route: String,
  arguments: List<NamedNavArgument> = emptyList(),
  content: @Composable (NavBackStackEntry) -> Unit,
) {
  composable(
    route = LEAF_SCREEN_PREFIX + route,
    arguments = arguments,
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
