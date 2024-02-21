package com.adewan.gamevault.features

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adewan.gamevault.theme.GameVaultTheme
import com.adewan.gamevault.utils.DarkPreview
import logcat.logcat

private const val s =
  "In Marvel's Spider-Man Remastered, we meet an experienced Peter Parker who's more masterful at fighting big crime in New York City. At the same time, he's struggling to balance his chaotic personal life and career while the fate of Marvel's New York rests upon his shoulders."

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameDetailView(slug: String) {
  LaunchedEffect(key1 = slug) { logcat { "Game Detail View for $slug" } }
  Scaffold(modifier = Modifier.padding(horizontal = 16.dp), topBar = { GameDetailTopBar() }) {
    Column(
      modifier = Modifier.fillMaxSize().padding(it).verticalScroll(rememberScrollState()),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      AsyncImage(
        model = "",
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier =
          Modifier.fillMaxWidth()
            .aspectRatio(1.5f)
            .clip(
              RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = 10.dp,
                bottomStart = 10.dp,
              )
            ),
        placeholder =
          BrushPainter(
            Brush.linearGradient(listOf(Color(color = 0xFFFFFFFF), Color(color = 0xFFDDDDDD)))
          ),
      )
      Text(
        text = "Spider Man Remastered",
        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
        modifier = Modifier.padding(vertical = 10.dp),
      )
      LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.sizeIn(minHeight = 100.dp, maxHeight = 250.dp),
        horizontalArrangement = spacedBy(10.dp),
        verticalArrangement = spacedBy(10.dp),
      ) {
        items(4) {
          Box(
            modifier =
              Modifier.size(75.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.onBackground)
          ) {
            Text(
              text = "Release Date",
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.SemiBold,
              modifier = Modifier.align(Alignment.TopCenter).padding(vertical = 10.dp),
              color = MaterialTheme.colorScheme.background,
            )
            Text(
              "05/24/10",
              style = MaterialTheme.typography.titleSmall,
              modifier = Modifier.align(Alignment.BottomCenter).padding(vertical = 10.dp),
              color = MaterialTheme.colorScheme.primaryContainer,
            )
          }
        }
      }

      Text(s, modifier = Modifier.padding(top = 10.dp))

      val pagerState = rememberPagerState(pageCount = { 10 })
      HorizontalPager(modifier = Modifier.padding(vertical = 10.dp), state = pagerState) { page ->
        AsyncImage(
          model = "",
          contentDescription = "",
          contentScale = ContentScale.Crop,
          modifier =
            Modifier.fillMaxSize()
              .aspectRatio(2f)
              .padding(horizontal = 5.dp)
              .clip(RoundedCornerShape(10.dp)),
          placeholder =
            BrushPainter(
              Brush.linearGradient(listOf(Color(color = 0xFFFFFFFF), Color(color = 0xFFDDDDDD)))
            ),
        )
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GameDetailTopBar() {
  CenterAlignedTopAppBar(
    title = {},
    navigationIcon = {
      IconButton(onClick = { /*TODO*/}) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "") }
    },
  )
}

@DarkPreview
@Composable
fun PreviewGameDetailView() {
  GameVaultTheme {
    Box(
      modifier =
        Modifier.fillMaxSize()
          .background(MaterialTheme.colorScheme.background)
          .padding(horizontal = 16.dp)
    ) {
      GameDetailView(slug = "slug")
    }
  }
}
