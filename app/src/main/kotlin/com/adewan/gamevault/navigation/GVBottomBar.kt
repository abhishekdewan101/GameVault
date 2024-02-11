package com.adewan.gamevault.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.adewan.gamevault.R
import com.adewan.gamevault.theme.GameVaultTheme
import com.adewan.gamevault.utils.DarkPreview

@Composable
fun GVBottomBar(navController: NavHostController) {
  NavigationBar {
    NavigationBarItem(
      selected = false,
      onClick = {},
      icon = {
        Icon(
          painterResource(id = R.drawable.sparkles),
          modifier = Modifier.size(24.dp),
          contentDescription = "",
        )
      },
      label = { Text("Discover") },
    )
    NavigationBarItem(
      selected = false,
      onClick = {},
      icon = { Icon(Icons.Default.Search, contentDescription = "") },
      label = { Text("Search") },
    )
    NavigationBarItem(
      selected = false,
      onClick = {},
      icon = {
        Icon(
          painterResource(id = R.drawable.vault),
          modifier = Modifier.size(24.dp),
          contentDescription = "",
        )
      },
      label = { Text("Vault") },
    )
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
