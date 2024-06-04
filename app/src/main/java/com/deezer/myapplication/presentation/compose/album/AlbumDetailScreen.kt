package com.deezer.myapplication.presentation.compose.album

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deezer.domain.remotemodel.track.TrackItem
import com.deezer.myapplication.presentation.compose.component.AudioPlayer
import com.deezer.myapplication.presentation.compose.component.ImageComponent
import com.deezer.myapplication.presentation.compose.component.SpacerComponent
import com.deezer.myapplication.presentation.compose.component.TextComponent
import com.deezer.myapplication.presentation.viewmodel.album.AlbumDetailIntent
import com.deezer.myapplication.presentation.viewmodel.album.AlbumDetailState
import com.deezer.myapplication.presentation.viewmodel.album.AlbumViewModel

@Composable
fun AlbumDetailScreen(
    albumId: String,
    viewModel: AlbumViewModel = hiltViewModel()
) {
    LaunchedEffect(albumId) {
        viewModel.intentChannel.send(AlbumDetailIntent.LoadAlbum(albumId))
    }

    val state by viewModel.state.collectAsState()

    when (state) {
        is AlbumDetailState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
        is AlbumDetailState.Success -> {
            val album = (state as AlbumDetailState.Success).albumList
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TextComponent(
                    text = album.title ?: "Unknown Album",
                    style = MaterialTheme.typography.headlineMedium
                )
                SpacerComponent(height = 16.dp)
                ImageComponent(
                    imageUrl = album.coverXl ?: "",
                    contentDescription = album.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                SpacerComponent(height = 16.dp)
                TextComponent(
                    text = "ID: ${album.id}",
                    style = MaterialTheme.typography.bodyLarge
                )
                SpacerComponent(height = 8.dp)
                TextComponent(
                    text = "Release Date: ${album.releaseDate}",
                    style = MaterialTheme.typography.bodyLarge
                )
                SpacerComponent(height = 8.dp)
                TextComponent(
                    text = "Number of Tracks: ${album.nbTracks}",
                    style = MaterialTheme.typography.bodyLarge
                )
                SpacerComponent(height = 16.dp)
                TextComponent(
                    text = "Tracks:",
                    style = MaterialTheme.typography.headlineSmall
                )
                LazyColumn {
                    items(requireNotNull(album.trackList?.trackItems)) { track ->
                        TrackItemRow(track)
                    }
                }
            }
        }
        is AlbumDetailState.Error -> {
            val errorMessage = (state as AlbumDetailState.Error).message
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun TrackItemRow(track: TrackItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            TextComponent(
                text = track.title ?: "Unknown Title",
                style = MaterialTheme.typography.bodyLarge
            )
            SpacerComponent(height = 8.dp)
            AudioPlayer(previewUrl = track.preview ?: "")
        }
    }
}
