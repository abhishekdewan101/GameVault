package com.adewan.gamevault.repositories

import com.adewan.gamevault.database.GameVaultDB
import com.adewan.gamevault.network.GameVaultClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
  @Provides
  @Singleton
  fun providesAuthenticationRepository(
    database: GameVaultDB,
    client: GameVaultClient,
  ): AuthenticationRepository {
    return AuthenticationRepository(database = database, client = client)
  }

  @Provides
  @Singleton
  fun provideGameRepository(
    authenticationRepository: AuthenticationRepository,
    client: GameVaultClient,
  ): GameRepository {
    return GameRepository(authenticationRepository = authenticationRepository, client = client)
  }

  @Provides
  @Singleton
  fun provideListRepository(
    authenticationRepository: AuthenticationRepository,
    client: GameVaultClient,
  ): ListViewRepository {
    return ListViewRepository(authenticationRepository = authenticationRepository, client = client)
  }
}
