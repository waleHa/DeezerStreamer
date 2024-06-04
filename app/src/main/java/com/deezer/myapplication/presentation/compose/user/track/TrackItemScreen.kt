package com.deezer.myapplication.presentation.compose.user.track

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deezer.domain.remotemodel.track.TrackItem
import com.deezer.myapplication.presentation.compose.component.*

@Composable
fun TrackItemScreen(track: TrackItem, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically

            ) {
                ImageComponent(
                    imageUrl = track.album?.coverMedium ?: "",
                    contentDescription = "Album Cover",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                SpacerComponent(height = 16.dp)
                Column {
                    TextComponent(
                        text = requireNotNull(track.title),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    SpacerComponent(height = 4.dp)
                    TextComponent(
                        text = "Artist: ${track.artist?.name}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    SpacerComponent(height = 4.dp)
                    TextComponent(
                        text = "Album: ${track.album?.title}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            SpacerComponent(height = 16.dp)
            AudioPlayer(previewUrl = requireNotNull(track.preview))
            SpacerComponent(height = 8.dp)
            HorizontalDividerComponent()
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun Preview(){
//    TrackItemScreen()
}