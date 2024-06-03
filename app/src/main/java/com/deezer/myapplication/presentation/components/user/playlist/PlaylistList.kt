package com.deezer.myapplication.presentation.components.user.playlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.deezer.domain.remotemodel.PlaylistItem

@Composable
fun PlaylistList(navController: NavController, playlists: List<PlaylistItem>, modifier: Modifier=Modifier) {
    LazyColumn {
        items(playlists) { playlist ->
            PlaylistItem(navController, playlist,modifier)
        }
    }
}