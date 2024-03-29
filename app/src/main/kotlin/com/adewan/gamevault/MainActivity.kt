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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.adewan.gamevault.effects.InsetColorEffect
import com.adewan.gamevault.navigation.GVBottomBar
import com.adewan.gamevault.navigation.GVNavHost
import com.adewan.gamevault.navigation.defaultBottomNavigationDestinations
import com.adewan.gamevault.navigation.hideBottomBarEffect
import com.adewan.gamevault.repositories.AuthenticationRepository
import com.adewan.gamevault.repositories.GameRepository
import com.adewan.gamevault.theme.GameVaultTheme
import com.adewan.gamevault.utils.DarkPreview
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import logcat.logcat
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject lateinit var authenticationRepository: AuthenticationRepository
  @Inject lateinit var gameRepository: GameRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { GameVault() }
    lifecycleScope.launch {
      authenticationRepository.authenticateUser()
      logcat { gameRepository.getTopRatedGames().joinToString { it.toString() } }
      logcat { gameRepository.getUpcomingGames().joinToString { it.toString() } }
      logcat { gameRepository.getRecentlyReleasedGames().joinToString { it.toString() } }
    }
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
  initialStartDestination: String = defaultBottomNavigationDestinations.first().route
) {
  val navController = rememberNavController()
  val shouldHideBottomBar = hideBottomBarEffect(navController = navController)
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    bottomBar = { if (!shouldHideBottomBar) GVBottomBar(navController = navController) },
  ) {
    GVNavHost(
      modifier = Modifier.padding(it),
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
