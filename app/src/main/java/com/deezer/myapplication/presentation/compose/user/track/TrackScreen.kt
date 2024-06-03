package com.deezer.myapplication.presentation.compose.user.track

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.deezer.myapplication.presentation.intent.TrackIntent
import com.deezer.myapplication.presentation.state.TrackState
import com.deezer.myapplication.presentation.viewmodel.TrackViewModel

@Composable
fun TrackScreen(playlistId: String, viewModel: TrackViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.intentChannel.send(TrackIntent.LoadTracks(playlistId))
    }

    val state by viewModel.state.collectAsState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (state) {
            is TrackState.Loading -> CircularProgressIndicator()
            is TrackState.Success -> TrackListScreen((state as TrackState.Success).tracks, Modifier)
            is TrackState.Error -> Text((state as TrackState.Error).message)
        }
    }
}

