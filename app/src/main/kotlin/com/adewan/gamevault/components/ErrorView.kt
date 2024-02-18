package com.adewan.gamevault.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Report
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adewan.gamevault.theme.GameVaultTheme
import com.adewan.gamevault.utils.DarkPreview

@Composable
fun ErrorView() {
  Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
    Column(
      modifier = Modifier.align(Alignment.Center),
      verticalArrangement = spacedBy(10.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Icon(
        Icons.Default.Report,
        contentDescription = "",
        tint = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.size(48.dp),
      )
      Text("Unfortunately we ran into an error!", color = MaterialTheme.colorScheme.onBackground)
    }
  }
}

@DarkPreview
@Composable
fun PreviewErrorView() {
  GameVaultTheme { ErrorView() }
}
