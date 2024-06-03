package com.deezer.myapplication.presentation.components.user.playlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.deezer.core.toReadableDate
import com.deezer.domain.remotemodel.PlaylistItem

@Composable
fun PlaylistItem(
    navController: NavController,
    playlist: PlaylistItem,
    modifier: Modifier = Modifier
) {
    val lazyColumnShadow = modifier
        .fillMaxWidth()
        .shadow(8.dp, RoundedCornerShape(8.dp))
        .background(MaterialTheme.colorScheme.surface)
        .padding(8.dp)

    Column(
        modifier = lazyColumnShadow
            .clickable {
                navController.navigate("tracks/${playlist.id}")
            }
            .padding(16.dp)
    ) {
        Text(text = requireNotNull(playlist.title), style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Created by: ${playlist.creator?.name}", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Creation date: ${playlist.creationDate?.toReadableDate()}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = rememberImagePainter(playlist.pictureXl),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}