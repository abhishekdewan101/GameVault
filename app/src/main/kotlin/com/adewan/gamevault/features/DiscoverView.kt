package com.adewan.gamevault.features

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adewan.gamevault.GameVaultInternal
import com.adewan.gamevault.theme.GameVaultTheme
import com.adewan.gamevault.utils.DarkPreview

@Composable
fun DiscoverView() {
  Scaffold(modifier = Modifier.fillMaxSize(), topBar = { DiscoverTopBar() }) {
    Column(modifier = Modifier.fillMaxSize().padding(it).verticalScroll(rememberScrollState())) {
      GameDiscoverHeroPager()
      FutureReleaseGames()
      NewlyReleasedGames()
    }
  }
}

@Composable
fun NewlyReleasedGames() {
  Column {
    GameListTitleRow(title = "Newly Released")
    BoxWithConstraints {
      LazyRow(
        modifier = Modifier.padding(vertical = 10.dp),
        contentPadding = PaddingValues(end = 16.dp),
        horizontalArrangement = spacedBy(10.dp),
      ) {
        items(50) {
          val width = maxWidth / 2.5f - 20.dp
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

@Composable
fun FutureReleaseGames() {
  Column {
    GameListTitleRow(title = "Future Releases")
    BoxWithConstraints {
      LazyRow(
        modifier = Modifier.padding(vertical = 10.dp),
        contentPadding = PaddingValues(end = 16.dp),
        horizontalArrangement = spacedBy(10.dp),
      ) {
        items(50) {
          val width = maxWidth / 2.5f - 20.dp
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

@Composable
private fun GameListTitleRow(title: String) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(
      title,
      style =
        MaterialTheme.typography.titleSmall.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
    )
    TextButton(onClick = {}) { Text("Sea all") }
  }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun GameDiscoverHeroPager() {
  val pagerState = rememberPagerState(pageCount = { 10 })
  HorizontalPager(modifier = Modifier.padding(vertical = 10.dp), state = pagerState) { page ->
    HeroPagerItem(page = page)
  }
}

@Composable
private fun HeroPagerItem(page: Int) {
  Row(modifier = Modifier.fillMaxWidth().padding(), verticalAlignment = Alignment.Top) {
    Box(
      modifier =
        Modifier.height(200.dp)
          .aspectRatio(3 / 4f)
          .clip(RoundedCornerShape(10.dp))
          .background(MaterialTheme.colorScheme.onBackground)
    )
    Column(
      horizontalAlignment = Alignment.Start,
      modifier = Modifier.padding(start = 10.dp),
      verticalArrangement = spacedBy(5.dp),
    ) {
      Text(
        "Game Title $page",
        modifier = Modifier.padding(vertical = 5.dp),
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
      )
      Text(
        "Rated 100.0 out of 100.0",
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
      )
      Text("Released 1 year ago", style = MaterialTheme.typography.bodyMedium)
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverTopBar() {
  CenterAlignedTopAppBar(
    title = { Text(text = "Discover", fontWeight = FontWeight.SemiBold) },
    actions = {
      IconButton(onClick = { /*TODO*/}) { Icon(Icons.Default.Search, "") }
      Box(
        modifier =
          Modifier.size(32.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable {}
      ) {
        Text(
          text = "AD",
          fontSize = 14.sp,
          color = MaterialTheme.colorScheme.onSecondaryContainer,
          fontWeight = FontWeight.SemiBold,
          modifier = Modifier.align(Alignment.Center),
        )
      }
    },
  )
}

@DarkPreview
@Composable
fun PreviewDiscoverView() {
  GameVaultTheme { GameVaultInternal() }
}
