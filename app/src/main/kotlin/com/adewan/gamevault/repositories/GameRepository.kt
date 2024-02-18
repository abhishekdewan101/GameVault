package com.adewan.gamevault.repositories

import com.adewan.gamevault.network.GameVaultClient
import com.adewan.gamevault.network.NetworkGame
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Duration
import java.time.Instant

class GameRepository(
  private val authenticationRepository: AuthenticationRepository,
  private val client: GameVaultClient,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

  suspend fun getTopRatedGames(): List<NetworkGame> {
    return withContext(dispatcher) {
      val query =
        "f name,slug, rating, hypes, first_release_date, cover.url;" +
          "w first_release_date >= ${Instant.now().minus(Duration.ofDays(365 * 2)).epochSecond} & platforms =(167,169,130,48);" +
          "s rating desc;" +
          "l 20;"
      client.getGamesForQuery(
        query = query,
        accessToken = authenticationRepository.currentAuth.accessToken,
      )
    }
  }

  suspend fun getUpcomingGames(): List<NetworkGame> {
    return withContext(dispatcher) {
      val query =
        "f name,slug, rating, hypes,first_release_date, cover.url;" +
          "w first_release_date >= ${Instant.now().epochSecond} & platforms =(167,169,130,48);" +
          "s hypes desc;" +
          "l 20;"
      client.getGamesForQuery(
        query = query,
        accessToken = authenticationRepository.currentAuth.accessToken,
      )
    }
  }

  suspend fun getRecentlyReleasedGames(): List<NetworkGame> {
    return withContext(dispatcher) {
      val query =
        "f name,slug, rating, hypes,first_release_date, cover.url;" +
          "w first_release_date <= ${Instant.now().epochSecond} & platforms =(167,169,130,48) & rating != null;" +
          "s first_release_date desc;" +
          "l 20;"
      client.getGamesForQuery(
        query = query,
        accessToken = authenticationRepository.currentAuth.accessToken,
      )
    }
  }
}
