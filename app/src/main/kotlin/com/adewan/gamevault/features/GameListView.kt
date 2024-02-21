package com.adewan.gamevault.features

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.adewan.gamevault.components.ErrorView
import com.adewan.gamevault.components.ProgressView
import com.adewan.gamevault.network.NetworkGame
import com.adewan.gamevault.theme.GameVaultTheme
import com.adewan.gamevault.utils.DarkPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun GameListView(
  title: String,
  games: Flow<PagingData<NetworkGame>>,
  navigateToGameDetail: (String) -> Unit,
  onBack: () -> Unit,
) {
  val games = games.collectAsLazyPagingItems()

  Scaffold(topBar = { GameListTopBar(title = title, onBack = onBack) }) {
    BoxWithConstraints(modifier = Modifier.padding(it)) {
      when (games.loadState.refresh) {
        is LoadState.Loading -> ProgressView()
        is LoadState.Error -> ErrorView()
        else -> {
          val width = maxWidth / 2.5f - 20.dp
          LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = spacedBy(10.dp),
            horizontalArrangement = spacedBy(10.dp),
          ) {
            items(
              games.itemCount,
              key = games.itemKey { game -> game.slug },
              contentType = games.itemContentType { "MyPagingItems" },
            ) { index ->
              val game = games[index]
              AsyncImage(
                model = game?.cover?.buildUrl(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier =
                  Modifier.sizeIn(minWidth = width)
                    .aspectRatio(3 / 4f)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { game?.slug?.let { it1 -> navigateToGameDetail(it1) } },
                placeholder =
                  BrushPainter(
                    Brush.linearGradient(
                      listOf(Color(color = 0xFFFFFFFF), Color(color = 0xFFDDDDDD))
                    )
                  ),
              )
            }
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListTopBar(title: String, onBack: () -> Unit) {
  CenterAlignedTopAppBar(
    title = { Text(title, fontWeight = FontWeight.SemiBold) },
    navigationIcon = {
      IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "") }
    },
  )
}

@DarkPreview
@Composable
fun PreviewGameListView() {
  GameVaultTheme {
    GameListView(title = "", games = flow {}, navigateToGameDetail = {}, onBack = {})
  }
}
