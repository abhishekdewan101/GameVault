package com.adewan.gamevault.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
  @Provides
  @Singleton
  fun provideGameVaultDB(@ApplicationContext context: Context): GameVaultDB {
    return Room.databaseBuilder(context = context, GameVaultDB::class.java, "gvdb").build()
  }
}
