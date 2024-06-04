package com.deezer.myapplication.presentation.compose.artist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import com.deezer.myapplication.presentation.compose.component.ImageComponent
import com.deezer.myapplication.presentation.compose.component.SpacerComponent
import com.deezer.myapplication.presentation.compose.component.TextComponent
import com.deezer.myapplication.presentation.viewmodel.artist.ArtistDetailIntent
import com.deezer.myapplication.presentation.viewmodel.artist.ArtistDetailState
import com.deezer.myapplication.presentation.viewmodel.artist.ArtistViewModel

@Composable
fun ArtistDetailScreen(
    artistId: String,
    viewModel: ArtistViewModel = hiltViewModel()
) {
    LaunchedEffect(artistId) {
        viewModel.intentChannel.send(ArtistDetailIntent.LoadArtist(artistId))
    }

    val state by viewModel.state.collectAsState()

    when (state) {
        is ArtistDetailState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
        is ArtistDetailState.Success -> {
            val artist = (state as ArtistDetailState.Success).artist
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TextComponent(
                    text = artist.name ?: "Unknown Artist",
                    style = MaterialTheme.typography.headlineMedium
                )
                SpacerComponent(height = 16.dp)
                ImageComponent(
                    imageUrl = artist.pictureXl ?: "",
                    contentDescription = artist.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
                SpacerComponent(height = 16.dp)
                TextComponent(text = "ID: ${artist.id}", style = MaterialTheme.typography.bodyLarge)
                SpacerComponent(height = 8.dp)
                TextComponent(
                    text = "Tracklist: ${artist.tracklist}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        is ArtistDetailState.Error -> {
            val errorMessage = (state as ArtistDetailState.Error).message
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