package com.adewan.gamevault.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.adewan.gamevault.network.NetworkGame
import com.adewan.gamevault.repositories.DEFAULT_LIST_VIEW_PAGE_SIZE
import com.adewan.gamevault.repositories.ListViewRepository
import com.adewan.gamevault.repositories.RecentlyReleasedQuery
import com.adewan.gamevault.repositories.TopRatedQuery
import com.adewan.gamevault.repositories.UpcomingQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

enum class ListType(val title: String, val query: String) {
  TOP_RATED("Top Rated", TopRatedQuery),
  RECENTLY_RELEASED("Recently Released", RecentlyReleasedQuery),
  RELEASING_SOON("Releasing Soon", UpcomingQuery)
}

@HiltViewModel
class GameListViewModel @Inject constructor(private val listViewRepository: ListViewRepository) :
  ViewModel() {

  fun loadGames(type: ListType): Flow<PagingData<NetworkGame>> {
    listViewRepository.query = type.query
    return Pager(
        config = PagingConfig(pageSize = DEFAULT_LIST_VIEW_PAGE_SIZE, prefetchDistance = 20),
        pagingSourceFactory = { listViewRepository },
      )
      .flow
      .cachedIn(viewModelScope)
  }
}
