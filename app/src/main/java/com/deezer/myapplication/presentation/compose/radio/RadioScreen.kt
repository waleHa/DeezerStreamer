package com.deezer.myapplication.presentation.compose.radio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deezer.domain.remotemodel.radio.RadioItem
import com.deezer.myapplication.presentation.compose.component.*
import com.deezer.myapplication.presentation.viewmodel.podcast.*
import com.deezer.myapplication.presentation.viewmodel.radio.RadioIntent
import com.deezer.myapplication.presentation.viewmodel.radio.RadioState
import com.deezer.myapplication.presentation.viewmodel.radio.RadioViewModel


@Composable
fun RadioScreen(
    navController: NavController,
    viewModel: RadioViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.intentChannel.send(RadioIntent.LoadRadios)
    }

    val state by viewModel.state.collectAsState()

    when (state) {
        is RadioState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
        is RadioState.Success -> {
            val radios = (state as RadioState.Success).radios
            LazyColumn {
                items(radios) { radio ->
                    RadioItemRow(radio)
                }
            }
        }
        is RadioState.Error -> {
            val errorMessage = (state as RadioState.Error).message
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
fun RadioItemRow(
    radio: RadioItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageComponent(
                imageUrl = radio.pictureXl ?: "",
                contentDescription = radio.title,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                TextComponent(text = radio.title ?: "Unknown Title", style = MaterialTheme.typography.headlineSmall)
            }
        }
    }
}
