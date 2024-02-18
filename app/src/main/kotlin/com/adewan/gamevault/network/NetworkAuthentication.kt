package com.adewan.gamevault.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkAuthentication(
  @SerialName("access_token") val accessToken: String,
  @SerialName("expires_in") val expiresIn: Long,
)
