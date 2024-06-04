package com.deezer.myapplication.presentation.compose.podcast

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deezer.core.toReadableDate
import com.deezer.domain.remotemodel.podcast.PodcastItem
import com.deezer.myapplication.presentation.compose.component.*
import com.deezer.myapplication.presentation.viewmodel.podcast.*
import com.deezer.myapplication.presentation.viewmodel.podcast.PodcastState
import com.deezer.myapplication.presentation.viewmodel.podcast.PodcastViewModel

@Composable
fun PodcastScreen(
    navController: NavController,
    viewModel: PodcastViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.intentChannel.send(PodcastIntent.LoadPodcasts)
    }

    val state by viewModel.state.collectAsState()

    when (state) {
        is PodcastState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
        is PodcastState.Success -> {
            val podcasts = (state as PodcastState.Success).podcasts
            LazyColumn {
                items(podcasts) { podcast ->
                    PodcastItemRow(podcast, navController)
                }
            }
        }
        is PodcastState.Error -> {
            val errorMessage = (state as PodcastState.Error).message
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun PodcastItemRow(
    podcast: PodcastItem,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("podcastDetail/${podcast.id}")
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageComponent(
                imageUrl = podcast.pictureXl ?: "",
                contentDescription = podcast.title,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                TextComponent(text = podcast.title ?: "Unknown Title", style = MaterialTheme.typography.headlineSmall)
            }
        }
    }
}

@Composable
fun PodcastDetailScreen(
    podcastId: String,
    viewModel: PodcastViewModel = hiltViewModel()
) {
    LaunchedEffect(podcastId) {
        viewModel.intentChannel.send(PodcastIntent.LoadPodcasts)
    }

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    when (state) {
        is PodcastState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
        is PodcastState.Success -> {
            val podcasts = (state as PodcastState.Success).podcasts
            val podcast = podcasts.find { it.id.toString() == podcastId }
            podcast?.let {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    TextComponent(text = it.title ?: "Unknown Title", style = MaterialTheme.typography.headlineMedium)
                    SpacerComponent(height = 16.dp)
                    ImageComponent(
                        imageUrl = it.pictureXl ?: "",
                        contentDescription = it.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                    SpacerComponent(height = 16.dp)
                    TextComponent(text = it.description ?: "No description available", style = MaterialTheme.typography.bodyLarge)
                    SpacerComponent(height = 8.dp)
                    TextComponent(text = "Fans: ${it.fans}", style = MaterialTheme.typography.bodyLarge)
                    SpacerComponent(height = 8.dp)
                    TextComponent(text = "Added on: ${it.timeAdd?.toReadableDate()}", style = MaterialTheme.typography.bodyLarge)
                    SpacerComponent(height = 16.dp)
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.link))
                        context.startActivity(intent)
                    }) {
                        Text("Go to Podcast")
                    }
                }
            }
        }
        is PodcastState.Error -> {
            val errorMessage = (state as PodcastState.Error).message
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}
