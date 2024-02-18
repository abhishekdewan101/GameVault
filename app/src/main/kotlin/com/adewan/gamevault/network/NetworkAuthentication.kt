package com.adewan.gamevault.network

import kotlinx.serialization.Serializable

@Serializable data class NetworkAuthentication(val accessToken: String, val expiresIn: Long)
