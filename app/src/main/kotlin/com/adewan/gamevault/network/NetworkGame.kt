package com.adewan.gamevault.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkGame(
  val slug: String,
  val name: String,
  val rating: Float? = null,
  val hypes: Int? = null,
  @SerialName("first_release_date") val releaseDate: Long? = null,
  val cover: NetworkGameCover? = null,
)

@Serializable data class NetworkGameCover(val id: Int, val url: String)
