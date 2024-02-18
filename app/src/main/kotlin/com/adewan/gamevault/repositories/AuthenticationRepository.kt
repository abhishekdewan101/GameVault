package com.adewan.gamevault.repositories

import com.adewan.gamevault.database.Authentication
import com.adewan.gamevault.database.GameVaultDB
import com.adewan.gamevault.network.GameVaultClient
import com.adewan.gamevault.network.NetworkAuthentication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import logcat.logcat
import java.time.Instant

class AuthenticationRepository(
  private val database: GameVaultDB,
  private val client: GameVaultClient,
  private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {

  lateinit var currentAuth: Authentication

  suspend fun authenticateUser() {
    withContext(dispatcher) {
      val localCurrentAuth = database.vaultDao().getAuthentication()
      if (!localCurrentAuth.isValid()) {
        logcat { "Getting authentication from network" }
        val networkAuth = client.getAuthenticationCredentials()
        val localAuth = networkAuth.toLocalAuth()
        database.vaultDao().clearAuthentication()
        database.vaultDao().insertAuthentication(localAuth)
        currentAuth = localAuth
      } else {
        currentAuth = localCurrentAuth!!
        logcat { "Valid authentication present in cache" }
      }
    }
  }
}

private fun Authentication?.isValid(): Boolean {
  if (this == null) return false
  val currentTime = Instant.now().epochSecond
  return currentTime < expiresBy
}

private fun NetworkAuthentication.toLocalAuth(): Authentication {
  val expiresBy = Instant.now().epochSecond + expiresIn
  return Authentication(accessToken = accessToken, expiresBy = expiresBy)
}
