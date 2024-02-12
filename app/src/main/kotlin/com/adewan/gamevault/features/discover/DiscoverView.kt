package com.adewan.gamevault.features.discover

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adewan.gamevault.theme.GameVaultTheme
import com.adewan.gamevault.utils.DarkPreview

@Composable
fun DiscoverView(navigateToSearch: () -> Unit) {
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = { DiscoverTopBar(navigateToSearch = navigateToSearch) },
  ) {
    LazyColumn(modifier = Modifier.padding(it)) {
      item { TopRatedGames() }
      item { NewlyReleasedGames() }
      item { FutureReleaseGames() }
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
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = spacedBy(10.dp),
      ) {
        items(50) {
          val width = maxWidth / 2.5f - 20.dp
          Box(
            modifier =
              Modifier.sizeIn(minWidth = width)
                .aspectRatio(3 / 4f)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
          )
        }
      }
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
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = spacedBy(10.dp),
      ) {
        items(50) {
          val width = maxWidth / 2.5f - 20.dp
          Box(
            modifier =
              Modifier.sizeIn(minWidth = width)
                .aspectRatio(3 / 4f)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
          )
        }
      }
    }
  }
}

@Composable
private fun GameListTitleRow(title: String) {
  Row(
    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopRatedGames() {
  val pagerState = rememberPagerState(pageCount = { 10 })
  BoxWithConstraints { HorizontalPager(state = pagerState) { HeroPagerItem(it) } }
}

@Composable
private fun HeroPagerItem(it: Int) {
  Column(
    modifier = Modifier.fillMaxWidth().padding(vertical = 40.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    Box(
      modifier =
        Modifier.height(250.dp)
          .aspectRatio(3 / 4f)
          .clip(RoundedCornerShape(10.dp))
          .background(Color.White)
    )
    Text(
      "Game Title $it",
      modifier = Modifier.padding(vertical = 5.dp),
      style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
    )
    Text(
      "Rated 100.0 out of 100.0",
      style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
    )
    Text("Released 1 year ago", style = MaterialTheme.typography.bodyMedium)
  }
}

@Composable
private fun DiscoverTopBar(navigateToSearch: () -> Unit) {
  Row(
    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Row {
      AvatarView()
      WelcomeMessage()
    }
    IconButton(onClick = navigateToSearch) { Icon(Icons.Default.Search, "") }
  }
}

@Composable
private fun WelcomeMessage() {
  Column {
    Text(
      "Welcome",
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
    )
    Text("Abhishek")
  }
}

@Composable
private fun AvatarView() {
  Box(
    modifier =
      Modifier.padding(end = 10.dp)
        .background(
          brush = Brush.linearGradient(listOf(Color.Red.copy(alpha = 0.8f), Color.Red)),
          shape = CircleShape,
        )
        .padding(8.dp)
  ) {
    Text(
      text = "AD",
      modifier = Modifier.align(Alignment.Center),
      style = TextStyle(fontSize = 16.sp),
    )
  }
}

@DarkPreview
@Composable
fun PreviewDiscoverView() {
  GameVaultTheme { DiscoverView(navigateToSearch = {}) }
}
