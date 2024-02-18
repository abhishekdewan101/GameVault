package com.adewan.gamevault.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VaultGame::class, Authentication::class], version = 1)
abstract class GameVaultDB : RoomDatabase() {
  abstract fun vaultDao(): VaultDao
}
