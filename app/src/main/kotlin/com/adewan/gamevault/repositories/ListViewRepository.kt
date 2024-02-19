package com.adewan.gamevault.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.adewan.gamevault.network.GameVaultClient
import com.adewan.gamevault.network.NetworkGame
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val DEFAULT_LIST_VIEW_PAGE_SIZE = 30

class ListViewRepository(
  private val client: GameVaultClient,
  private val authenticationRepository: AuthenticationRepository,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : PagingSource<Int, NetworkGame>() {
  var query: String? = ""

  override fun getRefreshKey(state: PagingState<Int, NetworkGame>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      val anchorPage = state.closestPageToPosition(anchorPosition)
      anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkGame> {
    return withContext(dispatcher) {
      val nextPageKey = params.key ?: 0
      val query = query + "offset ${DEFAULT_LIST_VIEW_PAGE_SIZE * nextPageKey};"
      val games =
        client.getGamesForQuery(
          query = query,
          accessToken = authenticationRepository.currentAuth.accessToken,
        )
      LoadResult.Page(data = games, prevKey = null, nextKey = nextPageKey + 1)
    }
  }
}
