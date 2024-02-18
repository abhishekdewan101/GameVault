package com.adewan.gamevault.repositories

import com.adewan.gamevault.database.GameVaultDB
import com.adewan.gamevault.network.GameVaultClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
  @Provides
  fun providesAuthenticationRepository(
    database: GameVaultDB,
    client: GameVaultClient,
  ): AuthenticationRepository {
    return AuthenticationRepository(database = database, client = client)
  }
}
