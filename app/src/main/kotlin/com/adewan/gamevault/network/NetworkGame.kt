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

@Serializable
data class NetworkGameCover(
  val id: Int,
  @SerialName("image_id") val imageId: String,
  val url: String,
) {
  fun buildUrl(): String {
    return "https://images.igdb.com/igdb/image/upload/t_720p/$imageId.jpg"
  }
}
