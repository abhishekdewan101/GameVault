package com.adewan.gamevault

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import logcat.AndroidLogcatLogger
import logcat.LogPriority

@HiltAndroidApp
class GameVaultApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    AndroidLogcatLogger.installOnDebuggableApp(this, minPriority = LogPriority.VERBOSE)
  }
}
