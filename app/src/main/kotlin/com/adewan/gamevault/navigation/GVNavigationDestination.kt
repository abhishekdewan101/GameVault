package com.adewan.gamevault.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.adewan.gamevault.R
import com.adewan.gamevault.features.DiscoverView
import com.adewan.gamevault.features.DiscoverViewModel
import com.adewan.gamevault.features.VaultView

data class GVBottomNavData(val label: String, val icon: @Composable () -> Painter)

data class GVNavigationDestination(
  val route: String,
  val bottomNavData: GVBottomNavData,
  val renderer: @Composable (NavHostController) -> Unit,
)

const val GAME_LIST_BASE = "gameList"

const val GAME_LIST_ROUTE = "$GAME_LIST_BASE?type={type}"

const val GAME_DETAIL_BASE = "gamedetail"

const val GAME_DETAIL_ROUTE = "gamedetail?slug={slug}"

val defaultBottomNavigationDestinations =
  listOf(
    GVNavigationDestination(
      route = "discover",
      bottomNavData =
        GVBottomNavData(label = "Discover", icon = { painterResource(id = R.drawable.sparkles) }),
      renderer = { navController ->
        val discoverViewModel = hiltViewModel<DiscoverViewModel>()
        LaunchedEffect(key1 = discoverViewModel) { discoverViewModel.loadDiscoverContent() }
        DiscoverView(
          uiState = discoverViewModel.uiState,
          navigateToGameDetail = { slug -> navController.navigateToGameDetail(slug = slug) },
          navigateToGameList = { navController.navigateToGameListView(it) },
        )
      },
    ),
    GVNavigationDestination(
      route = "vault",
      bottomNavData =
        GVBottomNavData(label = "Vault", icon = { painterResource(id = R.drawable.vault) }),
      renderer = { VaultView() },
    ),
  )
