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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.adewan.gamevault.components.ErrorView
import com.adewan.gamevault.components.ProgressView
import com.adewan.gamevault.network.NetworkGame
import com.adewan.gamevault.utils.UiState
import com.adewan.gamevault.utils.getTimeAgo

@Composable
fun DiscoverView(
  uiState: UiState,
  navigateToGameDetail: (String) -> Unit,
  navigateToGameList: (ListType) -> Unit,
) {
  Scaffold(
    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
    topBar = { DiscoverTopBar() },
  ) {
    when (uiState) {
      is UiState.Initial,
      is UiState.Loading -> ProgressView()
      is UiState.Result<*> -> {
        val data = uiState.data as DiscoverViewState
        Column(
          modifier = Modifier.fillMaxSize().padding(it).verticalScroll(rememberScrollState())
        ) {
          TopRatedGames(
            topRatedGames = data.topRatedGames,
            navigateToGameDetail = navigateToGameDetail,
          )
          FutureReleaseGames(
            upcomingGames = data.upcomingGames,
            navigateToGameList = navigateToGameList,
            navigateToGameDetail = navigateToGameDetail,
          )
          NewlyReleasedGames(
            recentlyReleasedGames = data.recentlyReleasedGames,
            navigateToGameList = navigateToGameList,
            navigateToGameDetail = navigateToGameDetail,
          )
        }
      }
      is UiState.Error -> ErrorView()
    }
  }
}

@Composable
fun NewlyReleasedGames(
  recentlyReleasedGames: DiscoverViewItem,
  navigateToGameList: (ListType) -> Unit,
  navigateToGameDetail: (String) -> Unit,
) {
  Column {
    GameListTitleRow(title = recentlyReleasedGames.title) {
      navigateToGameList(ListType.RECENTLY_RELEASED)
    }
    BoxWithConstraints {
      LazyRow(
        modifier = Modifier.padding(vertical = 10.dp),
        contentPadding = PaddingValues(end = 16.dp),
        horizontalArrangement = spacedBy(10.dp),
      ) {
        items(recentlyReleasedGames.listData) {
          val width = maxWidth / 2.5f - 20.dp
          AsyncImage(
            model = it.cover?.buildUrl(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier =
              Modifier.sizeIn(minWidth = width)
                .aspectRatio(3 / 4f)
                .clip(RoundedCornerShape(10.dp))
                .clickable { navigateToGameDetail(it.slug) },
            placeholder =
              BrushPainter(
                Brush.linearGradient(listOf(Color(color = 0xFFFFFFFF), Color(color = 0xFFDDDDDD)))
              ),
          )
        }
      }
    }
  }
}

@Composable
fun FutureReleaseGames(
  upcomingGames: DiscoverViewItem,
  navigateToGameList: (ListType) -> Unit,
  navigateToGameDetail: (String) -> Unit,
) {
  Column {
    GameListTitleRow(title = upcomingGames.title) { navigateToGameList(ListType.RELEASING_SOON) }
    BoxWithConstraints {
      LazyRow(
        modifier = Modifier.padding(vertical = 10.dp),
        contentPadding = PaddingValues(end = 16.dp),
        horizontalArrangement = spacedBy(10.dp),
      ) {
        items(upcomingGames.listData) {
          val width = maxWidth / 2.5f - 20.dp
          AsyncImage(
            model = it.cover?.buildUrl(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier =
              Modifier.sizeIn(minWidth = width)
                .aspectRatio(3 / 4f)
                .clip(RoundedCornerShape(10.dp))
                .clickable { navigateToGameDetail(it.slug) },
            placeholder =
              BrushPainter(
                Brush.linearGradient(listOf(Color(color = 0xFFFFFFFF), Color(color = 0xFFDDDDDD)))
              ),
          )
        }
      }
    }
  }
}

@Composable
private fun GameListTitleRow(title: String, onSeeAllClicked: () -> Unit) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(title, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
    TextButton(onClick = onSeeAllClicked) { Text("Sea all") }
  }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun TopRatedGames(topRatedGames: DiscoverViewItem, navigateToGameDetail: (String) -> Unit) {
  val pagerState = rememberPagerState(pageCount = { topRatedGames.listData.size })
  HorizontalPager(modifier = Modifier.padding(vertical = 10.dp), state = pagerState) { page ->
    HeroPagerItem(game = topRatedGames.listData[page], navigateToGameDetail = navigateToGameDetail)
  }
}

@Composable
private fun HeroPagerItem(game: NetworkGame, navigateToGameDetail: (String) -> Unit) {
  Row(
    modifier = Modifier.fillMaxWidth().padding().clickable { navigateToGameDetail(game.slug) },
    verticalAlignment = Alignment.Top,
  ) {
    AsyncImage(
      model = game.cover?.buildUrl(),
      contentDescription = "",
      contentScale = ContentScale.Crop,
      modifier = Modifier.height(200.dp).aspectRatio(3 / 4f).clip(RoundedCornerShape(10.dp)),
      placeholder =
        BrushPainter(
          Brush.linearGradient(listOf(Color(color = 0xFFFFFFFF), Color(color = 0xFFDDDDDD)))
        ),
    )
    Column(
      horizontalAlignment = Alignment.Start,
      modifier = Modifier.padding(start = 10.dp),
      verticalArrangement = spacedBy(5.dp),
    ) {
      Text(
        game.name,
        modifier = Modifier.padding(vertical = 5.dp),
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
      )
      Text(
        "Rated ${String.format("%.2f",game.rating)} out of 100.0",
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
      )
      Text(
        "Released ${game.releaseDate?.getTimeAgo()}",
        style = MaterialTheme.typography.bodyMedium,
      )
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
