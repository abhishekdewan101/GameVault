package com.adewan.gamevault.features.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adewan.gamevault.theme.GameVaultTheme
import com.adewan.gamevault.utils.DarkPreview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchView() {
  var searchFieldValue by remember { mutableStateOf(TextFieldValue("")) }
  Scaffold(
    modifier = Modifier,
    topBar = {
      SearchTopBar(
        searchFieldValue = searchFieldValue,
        updateSearchFieldValue = { searchFieldValue = it },
      )
    },
  ) {
    LazyColumn(
      modifier = Modifier.padding(it).padding(horizontal = 16.dp).padding(vertical = 10.dp),
      verticalArrangement = spacedBy(15.dp),
    ) {
      item {
        Row(
          modifier =
            Modifier.fillMaxWidth()
              .padding(top = 20.dp)
              .background(MaterialTheme.colorScheme.background)
        ) {
          Text(
            "Search results for ${searchFieldValue.text}...",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
          )
        }
      }

      items(50) {
        Row(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(10.dp))
              .background(MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp))
              .padding(vertical = 16.dp)
        ) {
          Box(
            modifier =
              Modifier.sizeIn(maxWidth = 100.dp)
                .aspectRatio(3 / 4f)
                .padding(start = 16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
          )
          Spacer(modifier = Modifier.width(10.dp))
          Column(
            modifier = Modifier.height(133.dp),
            verticalArrangement = Arrangement.SpaceBetween,
          ) {
            Column {
              Text(
                "The Last of Us Part II",
                style =
                  MaterialTheme.typography.titleMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                  ),
              )
              Text("Genre: Shooter", style = MaterialTheme.typography.titleSmall)
            }
            Row(
              modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
            ) {
              Column {
                Text(
                  "Rating",
                  style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                )
                Text("91/100", style = MaterialTheme.typography.bodySmall)
              }
              Column {
                Text(
                  "Rating",
                  style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                )
                Text("91/100", style = MaterialTheme.typography.bodySmall)
              }
              Column {
                Text(
                  "Rating",
                  style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                )
                Text("91/100", style = MaterialTheme.typography.bodySmall)
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun SearchTopBar(
  searchFieldValue: TextFieldValue,
  updateSearchFieldValue: (TextFieldValue) -> Unit,
) {
  TextField(
    value = searchFieldValue,
    onValueChange = updateSearchFieldValue,
    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
    maxLines = 1,
    singleLine = true,
    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    keyboardActions = KeyboardActions(onSearch = { println("This is searching something") }),
    colors =
      TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
      ),
    trailingIcon = {
      if (searchFieldValue.text.isNotEmpty()) {
        IconButton(onClick = { updateSearchFieldValue(TextFieldValue("")) }) {
          Icon(Icons.Default.Clear, "")
        }
      }
    },
    leadingIcon = { Icon(Icons.Default.Search, "") },
    shape = RoundedCornerShape(20.dp),
  )
}

@DarkPreview
@Composable
fun PreviewSearchView() {
  GameVaultTheme { SearchView() }
}
