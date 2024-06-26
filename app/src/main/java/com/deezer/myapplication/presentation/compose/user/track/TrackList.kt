package com.deezer.myapplication.presentation.compose.user.track

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.deezer.domain.remotemodel.track.TrackItem

@Composable
fun TrackListScreen(tracks: List<TrackItem>, modifier: Modifier) {
    LazyColumn {
        items(tracks) { track ->
            TrackItemScreen(track, modifier = modifier)
        }
    }
}

