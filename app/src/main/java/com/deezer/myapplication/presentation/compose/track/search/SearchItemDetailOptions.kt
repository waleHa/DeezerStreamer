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

@Composable
fun SearchItemDetailOptions(
    searchItemId: String,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                navController.navigate("artistDetail/$searchItemId")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("Show Artist")
        }
        Button(
            onClick = {
                navController.navigate("albumDetail/$searchItemId")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Show Album")
        }
    }
}