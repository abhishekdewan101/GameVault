package com.adewan.gamevault.network

import com.adewan.gamevault.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.InternalAPI
import kotlinx.serialization.json.Json
import logcat.logcat
import javax.inject.Inject

class GameVaultClient @Inject constructor() {
  private val client =
    HttpClient(CIO) {
      install(ContentNegotiation) {
        json(
          Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
          }
        )
      }
      install(Logging) {
        logger =
          object : Logger {
            override fun log(message: String) {
              logcat(tag = this::class.simpleName) { message }
            }
          }
        level =
          if (BuildConfig.DEBUG) {
            LogLevel.ALL
          } else {
            LogLevel.INFO
          }
      }
    }

  suspend fun getAuthenticationCredentials(): NetworkAuthentication {
    val url =
      "https://id.twitch.tv/oauth2/token?client_id=${BuildConfig.clientId}&client_secret=${BuildConfig.clientSecret}&grant_type=client_credentials"
    return client.post(url).body()
  }

  @OptIn(InternalAPI::class)
  suspend fun getGamesForQuery(query: String, accessToken: String): List<NetworkGame> {
    val url = "https://api.igdb.com/v4/games/"
    return client
      .post(url) {
        headers {
          append("Client-ID", BuildConfig.clientId)
          append("Authorization", "Bearer $accessToken")
        }
        body = query
      }
      .body()
  }
}
