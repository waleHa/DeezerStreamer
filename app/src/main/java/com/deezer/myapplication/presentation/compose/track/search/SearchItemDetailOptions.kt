package com.deezer.myapplication.presentation.compose.track.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deezer.core.Constant



@Composable
fun SearchItemDetailOptions(
    artistId: String,
    albumId: String,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                Constant.ARTIST_DEFAULT = artistId
                navController.navigate("artist/$artistId")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("Show Artist")
        }
        Button(
            onClick = {
                Constant.ALBUM_DEFAULT = albumId
                navController.navigate("album/$albumId")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Show Album")
        }
    }
}
