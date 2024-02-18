package com.adewan.gamevault.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface VaultDao {
  @Query("SELECT * FROM VaultGame") fun getAllVaultGames(): Flow<List<VaultGame>>

  @Insert fun insertVaultGame(vaultGame: VaultGame)

  @Delete fun deleteVaultGame(vaultGame: VaultGame)

  @Upsert fun updateVaultGame(vaultGame: VaultGame)

  @Query("SELECT * FROM Authentication") fun getAuthentication(): Authentication?

  @Query("DELETE FROM Authentication") fun clearAuthentication()

  @Insert fun insertAuthentication(authentication: Authentication)
}
