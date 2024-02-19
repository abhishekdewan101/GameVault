package com.adewan.gamevault.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.adewan.gamevault.theme.GameVaultTheme
import com.adewan.gamevault.utils.DarkPreview

@Composable
fun GVBottomBar(navController: NavHostController) {
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination
  NavigationBar {
    defaultBottomNavigationDestinations.forEach { dest ->
      NavigationBarItem(
        selected = dest.route == currentDestination?.route,
        onClick = { navController.navigate(dest.route) { navigateBottomBar(navController) } },
        icon = {
          Icon(dest.bottomNavData.icon(), modifier = Modifier.size(24.dp), contentDescription = "")
        },
        label = { Text(dest.bottomNavData.label) },
      )
    }
  }
}

@DarkPreview
@Composable
fun PreviewGVBottomBar() {
  val navController = rememberNavController()
  GameVaultTheme {
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { GVBottomBar(navController) }) {
      Box(modifier = Modifier.padding(it).fillMaxSize())
    }
  }
}
