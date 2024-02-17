package com.adewan.gamevault.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.adewan.gamevault.R
import com.adewan.gamevault.features.DiscoverView

data class GVBottomNavData(val label: String, val icon: @Composable () -> Painter)

data class GVNavigationDestination(
  val route: String,
  val bottomNavData: GVBottomNavData,
  val renderer: @Composable (NavHostController) -> Unit,
)

val defaultNavigationDestinations =
  listOf(
    GVNavigationDestination(
      route = "discover",
      bottomNavData =
        GVBottomNavData(label = "Discover", icon = { painterResource(id = R.drawable.sparkles) }),
      renderer = { navController -> DiscoverView() },
    ),
    GVNavigationDestination(
      route = "vault",
      bottomNavData =
        GVBottomNavData(label = "Vault", icon = { painterResource(id = R.drawable.vault) }),
      renderer = { Text("Vault") },
    ),
  )
