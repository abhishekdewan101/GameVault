package com.adewan.gamevault.repositories

import com.adewan.gamevault.network.GameVaultClient
import com.adewan.gamevault.network.NetworkGame
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Duration
import java.time.Instant

val TopRatedQuery =
  "f name,slug, rating, hypes, first_release_date, cover.*;" +
    "w first_release_date >= ${Instant.now().minus(Duration.ofDays(365 * 2)).epochSecond} & platforms =(167,169,130,48);" +
    "s rating desc;" +
    "l 30;"

val UpcomingQuery =
  "f name,slug, rating, hypes,first_release_date, cover.*;" +
    "w first_release_date >= ${Instant.now().epochSecond} & platforms =(167,169,130,48);" +
    "s hypes desc;" +
    "l 20;"

val RecentlyReleasedQuery =
  "f name,slug, rating, hypes,first_release_date, cover.*;" +
    "w first_release_date <= ${Instant.now().epochSecond} & platforms =(167,169,130,48) & rating != null;" +
    "s first_release_date desc;" +
    "l 20;"

class GameRepository(
  private val authenticationRepository: AuthenticationRepository,
  private val client: GameVaultClient,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

  suspend fun getTopRatedGames(): List<NetworkGame> {
    return withContext(dispatcher) {
      client.getGamesForQuery(
        query = TopRatedQuery,
        accessToken = authenticationRepository.currentAuth.accessToken,
      )
    }
  }

  suspend fun getUpcomingGames(): List<NetworkGame> {
    return withContext(dispatcher) {
      client.getGamesForQuery(
        query = UpcomingQuery,
        accessToken = authenticationRepository.currentAuth.accessToken,
      )
    }
  }

  suspend fun getRecentlyReleasedGames(): List<NetworkGame> {
    return withContext(dispatcher) {
      client.getGamesForQuery(
        query = RecentlyReleasedQuery,
        accessToken = authenticationRepository.currentAuth.accessToken,
      )
    }
  }
}
