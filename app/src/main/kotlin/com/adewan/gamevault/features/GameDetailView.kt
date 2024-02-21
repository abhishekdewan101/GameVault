package com.adewan.gamevault.features

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.adewan.gamevault.theme.GameVaultTheme
import com.adewan.gamevault.utils.DarkPreview
import logcat.logcat

@Composable
fun GameDetailView(slug: String) {
  LaunchedEffect(key1 = slug) { logcat { "Game Detail View for $slug" } }
}

@DarkPreview
@Composable
fun PreviewGameDetailView() {
  GameVaultTheme { GameDetailView(slug = "Slug") }
}
