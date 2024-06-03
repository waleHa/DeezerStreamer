package com.deezer.myapplication.presentation.compose.user.track

import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView



@Composable
fun AudioPlayer(previewUrl: String) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(previewUrl))
            prepare()
            playWhenReady = false // Start in pause state
        }
    }

    DisposableEffect(
        key1 = previewUrl,
        effect = {
            onDispose {
                exoPlayer.release()
            }
        }
    )

    AndroidView(
        factory = {
            PlayerView(context).apply {
                player = exoPlayer
                useController = true // Display play/pause controls
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp) // Adjust the height to make it smaller
    )
}