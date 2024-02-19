package com.adewan.gamevault.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.adewan.gamevault.theme.GameVaultTheme
import com.adewan.gamevault.utils.DarkPreview

@Composable
fun GameListView(onBack: () -> Unit) {
  Scaffold(topBar = { GameListTopBar(onBack = onBack) }) {
    BoxWithConstraints(modifier = Modifier.padding(it)) {
      val width = maxWidth / 2.5f - 20.dp
      LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = spacedBy(10.dp),
        horizontalArrangement = spacedBy(10.dp),
      ) {
        items(100) {
          Box(
            modifier =
              Modifier.sizeIn(minWidth = width)
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
fun GameListTopBar(onBack: () -> Unit) {
  CenterAlignedTopAppBar(
    title = { Text("Game List Title", fontWeight = FontWeight.SemiBold) },
    navigationIcon = {
      IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "") }
    },
  )
}

@DarkPreview
@Composable
fun PreviewGameListView() {
  GameVaultTheme { GameListView(onBack = {}) }
}
