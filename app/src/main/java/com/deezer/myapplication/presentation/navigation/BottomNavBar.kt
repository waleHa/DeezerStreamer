package com.deezer.myapplication.presentation.navigation


import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.AirplaneTicket
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.automirrored.filled.BrandingWatermark
import androidx.compose.material.icons.automirrored.filled.CallSplit
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Grading
import androidx.compose.material.icons.automirrored.filled.ManageSearch
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.MergeType
import androidx.compose.material.icons.automirrored.filled.PlaylistPlay
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.PlaylistPlay
import androidx.compose.material.icons.filled.Podcasts
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.deezer.core.AuthManager
import com.deezer.core.Constant
import com.google.firebase.auth.FirebaseAuth

@Composable
fun BottomNavBar(navController: NavController) {
    BottomAppBar(Modifier.background(MaterialTheme.colorScheme.primary)) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.LibraryMusic, contentDescription = "Playlists") },
            label = { Text("Playlists") },
            selected = false,
            onClick = {
                navController.navigate("playlists") {
                    popUpTo("playlists") { inclusive = true }
                }
            }
        )


        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            label = { Text("Search") },
            selected = false,
            onClick = {
                navController.navigate("search") {
                    popUpTo("search") { inclusive = true }
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Album, contentDescription = "Albums") },
            label = { Text("Albums") },
            selected = false,
            onClick = {
                navController.navigate("album/${Constant.ALBUM_DEFAULT}") {
                    popUpTo("album") { inclusive = true }
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.PersonOutline, contentDescription = "Artists") },
            label = { Text("Artists") },
            selected = false,
            onClick = {
                navController.navigate("artist/${Constant.ARTIST_DEFAULT}") {
                    popUpTo("artist") { inclusive = true }
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Radio, contentDescription = "Playlists") },
            label = { Text("Radio") },
            selected = false,
            onClick = {
                navController.navigate("radio") {
                    popUpTo("radio") { inclusive = true }
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Podcasts, contentDescription = "Podcast") },
            label = { Text("Podcast") },
            selected = false,
            onClick = {
                navController.navigate("podcasts") {
                    popUpTo("podcasts") { inclusive = true }
                }
            }
        )
    }
}