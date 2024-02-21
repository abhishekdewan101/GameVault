package com.adewan.gamevault.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.adewan.gamevault.GameVaultInternal
import com.adewan.gamevault.navigation.defaultBottomNavigationDestinations
import com.adewan.gamevault.theme.GameVaultTheme
import com.adewan.gamevault.utils.DarkPreview

@Composable
fun VaultView() {
  Scaffold(modifier = Modifier.padding(horizontal = 16.dp), topBar = { VaultTopBar() }) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize().padding(it)) {
      val minItemWidth = (maxWidth - 10.dp.times(2)) / 3
      LazyVerticalGrid(
        columns = GridCells.Adaptive(minItemWidth),
        horizontalArrangement = spacedBy(10.dp),
        verticalArrangement = spacedBy(10.dp),
        contentPadding = PaddingValues(bottom = 20.dp, top = 20.dp),
      ) {
        items(50) {
          Box(
            modifier =
              Modifier.width(minItemWidth)
                .aspectRatio(3 / 4f)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.onBackground)
          )
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VaultTopBar() {
  CenterAlignedTopAppBar(
    title = { Text(text = "Vault", fontWeight = FontWeight.SemiBold) },
    actions = { IconButton(onClick = {}) { Icon(Icons.Default.FilterList, "") } },
  )
}

@DarkPreview
@Composable
fun PreviewVaultView() {
  GameVaultTheme {
    GameVaultInternal(initialStartDestination = defaultBottomNavigationDestinations[1].route)
  }
}
