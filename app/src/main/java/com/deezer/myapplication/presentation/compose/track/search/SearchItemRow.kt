package com.deezer.myapplication.presentation.compose.track.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deezer.domain.remotemodel.track.SearchItem
import com.deezer.myapplication.presentation.compose.component.AudioPlayer

@Composable
fun SearchItemRow(
    searchItem: SearchItem,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Navigate to detail screen with artist and album ID
                navController.navigate("searchItemDetail/${searchItem.artist?.id}/${searchItem.album?.id}")
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = searchItem.title ?: "Unknown Title",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Duration: ${searchItem.duration}", // Assuming duration is in seconds
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Rank: ${searchItem.rank}", // Assuming rank is an integer
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            // Preview audio player
            AudioPlayer(previewUrl = searchItem.preview ?: "")
        }
    }
}