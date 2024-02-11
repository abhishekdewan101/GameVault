package com.adewan.gamevault.effects

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/** Controls the inset colors replacing system-ui controller from accompanist */
@Composable
fun InsetColorEffect(statusBarColor: Color, navigationBarColor: Color = statusBarColor) {
  val view = LocalView.current
  val isDarkTheme = isSystemInDarkTheme()
  SideEffect {
    val window = (view.context as Activity).window
    window.statusBarColor = statusBarColor.toArgb()
    window.navigationBarColor = navigationBarColor.toArgb()
    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isDarkTheme
  }
}
