package com.adewan.gamevault.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VaultGame(
  @PrimaryKey val slug: String,
  val name: String,
  val coverImageUri: String,
  val status: GameStatus,
)
