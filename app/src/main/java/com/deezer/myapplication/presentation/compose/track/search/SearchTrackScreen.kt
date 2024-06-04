package com.deezer.myapplication.presentation.compose.track.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deezer.domain.remotemodel.track.SearchItem
import com.deezer.myapplication.presentation.compose.component.*
import com.deezer.myapplication.presentation.viewmodel.artist.ArtistDetailIntent
import com.deezer.myapplication.presentation.viewmodel.artist.ArtistDetailState
import com.deezer.myapplication.presentation.viewmodel.artist.ArtistViewModel
import com.deezer.myapplication.presentation.viewmodel.track.SearchState
import com.deezer.myapplication.presentation.viewmodel.track.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SearchTrackScreen(
    navController: NavController,
    modifier: Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    var keyword by remember { mutableStateOf("") } // Remember and track the keyword entered by the user

    val state by viewModel.state.collectAsState()

    // Coroutine scope for launching coroutines
    val coroutineScope = rememberCoroutineScope()

    // Job to handle the debounce timer
    var searchJob by remember { mutableStateOf<Job?>(null) }

    Column(modifier = modifier.fillMaxSize()) {
        // Search box
        OutlinedTextFieldComponent(
            value = keyword,
            onValueChange = {
                keyword = it // Update the keyword when the user types
                // Cancel the previous search job if it exists
                searchJob?.cancel()
                // Start a new search job after a delay of 4 seconds
                searchJob = coroutineScope.launch {
                    delay(4000) // Wait for 4 seconds
                    viewModel.searchTracks(keyword) // Fetch data from ViewModel
                }
            },
            label = "Search",
            leadingIcon = Icons.Default.Search,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Search results
        when (state) {
            is SearchState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(36.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }
            is SearchState.Success -> {
                val searchItems = (state as SearchState.Success).searchItems
                LazyColumn {
                    items(searchItems) { searchItem ->
                        SearchItemRow(searchItem, navController)
                    }
                }
            }
            is SearchState.Error -> {
                val errorMessage = (state as SearchState.Error).message
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun SearchItemRow(
    searchItem: SearchItem,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Navigate to detail screen with search item ID
                navController.navigate("searchItemDetail/${searchItem.id}")
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = searchItem.title ?: "Unknown Title",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Duration: ${searchItem.duration}", // Assuming duration is in seconds
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Rank: ${searchItem.rank}", // Assuming rank is an integer
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            // Preview audio player
            AudioPlayer(previewUrl = searchItem.preview ?: "")
        }
    }
}

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


@Composable
fun ArtistDetailScreen(
    artistId: String,
    viewModel: ArtistViewModel = hiltViewModel()
) {
    LaunchedEffect(artistId) {
        viewModel.intentChannel.send(ArtistDetailIntent.LoadArtist(artistId))
    }

    val state by viewModel.state.collectAsState()

    when (state) {
        is ArtistDetailState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
        is ArtistDetailState.Success -> {
            val artist = (state as ArtistDetailState.Success).artist
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TextComponent(text = artist.name ?: "Unknown Artist", style = MaterialTheme.typography.headlineMedium)
                SpacerComponent(height = 16.dp)
                ImageComponent(
                    imageUrl = artist.pictureXl ?: "",
                    contentDescription = artist.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
                SpacerComponent(height = 16.dp)
                TextComponent(text = "ID: ${artist.id}", style = MaterialTheme.typography.bodyLarge)
                SpacerComponent(height = 8.dp)
                TextComponent(text = "Tracklist: ${artist.tracklist}", style = MaterialTheme.typography.bodyLarge)
            }
        }
        is ArtistDetailState.Error -> {
            val errorMessage = (state as ArtistDetailState.Error).message
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



