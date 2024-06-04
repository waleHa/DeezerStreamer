package com.deezer.myapplication.presentation.compose.user.playlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deezer.core.toReadableDate
import com.deezer.domain.remotemodel.playlist.PlaylistItem
import com.deezer.myapplication.presentation.compose.component.ImageComponent
import com.deezer.myapplication.presentation.compose.component.SpacerComponent
import com.deezer.myapplication.presentation.compose.component.TextComponent

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

    Card(
        modifier = lazyColumnShadow
            .clickable {
                navController.navigate("tracks/${playlist.id}")
            },
        elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            TextComponent(
                text = requireNotNull(playlist.title),
                style = MaterialTheme.typography.titleSmall
            )
            SpacerComponent(height = 8.dp)
            TextComponent(
                text = "Created by: ${playlist.creator?.name}",
                style = MaterialTheme.typography.headlineSmall
            )
            SpacerComponent(height = 4.dp)
            TextComponent(
                text = "Creation date: ${playlist.creationDate?.toReadableDate()}",
                style = MaterialTheme.typography.bodyMedium
            )
            SpacerComponent(height = 8.dp)
            ImageComponent(
                imageUrl = requireNotNull(playlist.pictureXl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}
