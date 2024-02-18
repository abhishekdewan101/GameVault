package com.adewan.gamevault.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity data class Authentication(@PrimaryKey val accessToken: String, val expiresBy: Long)
