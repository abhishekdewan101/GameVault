package com.adewan.gamevault.network

import com.adewan.gamevault.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface NetworkClient {
  suspend fun getAuthenticationCredentials(): NetworkAuthentication
}

class GameVaultClient : NetworkClient {
  private val client =
    HttpClient(CIO) {
      install(ContentNegotiation) {
        json(
          Json {
            prettyPrint = true
            isLenient = true
          }
        )
      }
    }

  override suspend fun getAuthenticationCredentials(): NetworkAuthentication {
    val url =
      "https://id.twitch.tv/oauth2/token?client_id=${BuildConfig.clientId}&client_secret=${BuildConfig.clientSecret}&grant_type=client_credentials"
    return client.post(url).body()
  }
}
