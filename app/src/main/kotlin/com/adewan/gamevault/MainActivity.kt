package com.adewan.gamevault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.adewan.gamevault.effects.InsetColorEffect
import com.adewan.gamevault.navigation.GVBottomBar
import com.adewan.gamevault.navigation.GVNavHost
import com.adewan.gamevault.navigation.defaultNavigationDestinations
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
    InsetColorEffect(
      statusBarColor = MaterialTheme.colorScheme.background,
      navigationBarColor =
        MaterialTheme.colorScheme.surfaceColorAtElevation(
          elevation = BottomAppBarDefaults.ContainerElevation
        ),
    )

    GameVaultInternal()
  }
}

@Composable
fun GameVaultInternal(
  initialStartDestination: String = defaultNavigationDestinations.first().route
) {
  val navController = rememberNavController()
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    bottomBar = { GVBottomBar(navController = navController) },
  ) {
    GVNavHost(
      modifier = Modifier.padding(it).padding(horizontal = 16.dp),
      navController = navController,
      startDestination = initialStartDestination,
    )
  }
}

@DarkPreview
@Composable
fun PreviewGameVault() {
  GameVaultTheme { GameVaultInternal() }
}

@Preview
@Composable
fun PreviewGameVaultLight() {
  GameVaultTheme { GameVaultInternal() }
}
