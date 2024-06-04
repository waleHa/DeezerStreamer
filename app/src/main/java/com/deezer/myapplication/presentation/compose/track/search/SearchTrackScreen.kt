package com.deezer.myapplication.presentation.compose.track.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deezer.myapplication.presentation.compose.component.*
import com.deezer.myapplication.presentation.viewmodel.track.SearchState
import com.deezer.myapplication.presentation.viewmodel.track.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SearchTrackScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
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
        OutlinedTextField(
            value = keyword,
            onValueChange = {
                keyword = it // Update the keyword when the user types
                // Cancel the previous search job if it exists
                searchJob?.cancel()
                // Start a new search job after a delay of 1 second
                searchJob = coroutineScope.launch {
                    delay(1000) // Wait for 1 second
                    viewModel.searchTracks(keyword) // Fetch data from ViewModel
                }
            },
            label = { Text("Search") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            singleLine = true, // Set single line
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    searchJob?.cancel()
                    searchJob = coroutineScope.launch {
                        viewModel.searchTracks(keyword) // Fetch data from ViewModel
                    }
                }
            ),
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



