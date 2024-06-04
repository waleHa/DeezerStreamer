package com.deezer.myapplication.presentation.compose.user.playlist

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
import androidx.navigation.NavController
import com.deezer.myapplication.presentation.viewmodel.playlist.PlaylistIntent
import com.deezer.myapplication.presentation.viewmodel.playlist.PlaylistState
import com.deezer.myapplication.presentation.viewmodel.playlist.PlaylistViewModel

@Composable
fun PlaylistScreen(navController: NavController, viewModel: PlaylistViewModel = hiltViewModel()) {
    // Trigger the loading of playlists when the composition is committed
    LaunchedEffect(Unit) {
        viewModel.intentChannel.send(PlaylistIntent.LoadPlaylists)
    }
    val state by viewModel.state.collectAsState()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (state) {
            is PlaylistState.Loading -> CircularProgressIndicator()
            is PlaylistState.Success -> PlaylistList(
                navController,
                (state as PlaylistState.Success).playlists
            )

            is PlaylistState.Error -> Text((state as PlaylistState.Error).message)
        }
    }
}