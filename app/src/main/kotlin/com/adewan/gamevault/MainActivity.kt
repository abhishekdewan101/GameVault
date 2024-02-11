package com.adewan.gamevault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.adewan.gamevault.navigation.GVBottomBar
import com.adewan.gamevault.navigation.GVNavHost
import com.adewan.gamevault.theme.GameVaultTheme
import com.adewan.gamevault.utils.DarkPreview

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { GameVault() }
  }
}

@Composable
private fun GameVault() {
  GameVaultTheme {
    val navController = rememberNavController()
    Scaffold(
      modifier = Modifier.fillMaxSize(),
      bottomBar = { GVBottomBar(navController = navController) },
    ) {
      GVNavHost(
        modifier = Modifier.padding(it),
        navController = navController,
        startDestination = "home",
      )
    }
  }
}

@DarkPreview
@Composable
fun PreviewGameVault() {
  GameVault()
}
