package com.deezer.myapplication.presentation.compose.artist

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.deezer.domain.remotemodel.artist.Artist
import com.deezer.domain.remotemodel.track.TrackItem
import com.deezer.myapplication.presentation.compose.album.TrackItemRow
import com.deezer.myapplication.presentation.compose.component.ImageComponent
import com.deezer.myapplication.presentation.compose.component.SpacerComponent
import com.deezer.myapplication.presentation.compose.component.TextComponent
import com.deezer.myapplication.presentation.viewmodel.artist.ArtistDetailIntent
import com.deezer.myapplication.presentation.viewmodel.artist.ArtistDetailState
import com.deezer.myapplication.presentation.viewmodel.artist.ArtistViewModel


@Composable
fun ArtistScreen(
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
            val tracks = (state as ArtistDetailState.Success).tracks
            ArtistDetailContent(artist, tracks)
        }

        is ArtistDetailState.Error -> {
            val errorMessage = (state as ArtistDetailState.Error).message
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Composable
fun ArtistDetailContent(
    artist: Artist,
    tracks: List<TrackItem>,
    modifier: Modifier = Modifier
) {
    Log.d("ArtistDetailContent", "Displaying artist: ${artist.name}")
    Log.d("ArtistDetailContent", "Number of tracks: ${tracks.size}")

    Column(
        modifier = modifier
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

        SpacerComponent(height = 16.dp)
        TextComponent(
            text = "Tracks:",
            style = MaterialTheme.typography.headlineSmall
        )
        LazyColumn {
            items(tracks) { track ->
                TrackItemRow(track)
            }
        }
    }
}