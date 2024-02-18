package com.adewan.gamevault.features

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adewan.gamevault.network.NetworkGame
import com.adewan.gamevault.repositories.GameRepository
import com.adewan.gamevault.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DiscoverViewItem(val title: String, val listData: List<NetworkGame>)

data class DiscoverViewState(
  val topRatedGames: DiscoverViewItem,
  val upcomingGames: DiscoverViewItem,
  val recentlyReleasedGames: DiscoverViewItem,
)

@HiltViewModel
class DiscoverViewModel @Inject constructor(private val gameRepository: GameRepository) :
  ViewModel() {
  var uiState by mutableStateOf<UiState>(UiState.Initial)
    private set

  fun loadDiscoverContent() {
    uiState = UiState.Loading
    viewModelScope.launch {
      val topRatedGames = async { gameRepository.getTopRatedGames() }
      val upcomingGames = async { gameRepository.getUpcomingGames() }
      val recentlyReleasedGames = async { gameRepository.getRecentlyReleasedGames() }
      uiState =
        UiState.Result(
          data =
            DiscoverViewState(
              topRatedGames =
                DiscoverViewItem(title = "Top Rated", listData = topRatedGames.await()),
              upcomingGames =
                DiscoverViewItem(title = "Releasing Soon", listData = upcomingGames.await()),
              recentlyReleasedGames =
                DiscoverViewItem(
                  title = "Recently Released",
                  listData = recentlyReleasedGames.await(),
                ),
            )
        )
    }
  }
}
