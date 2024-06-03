package com.deezer.myapplication.presentation.navigation


import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.AirplaneTicket
import androidx.compose.material.icons.automirrored.filled.BrandingWatermark
import androidx.compose.material.icons.automirrored.filled.CallSplit
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Grading
import androidx.compose.material.icons.automirrored.filled.ManageSearch
import androidx.compose.material.icons.automirrored.filled.MergeType
import androidx.compose.material.icons.automirrored.filled.PlaylistPlay
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.PlaylistPlay
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.deezer.core.AuthManager
import com.google.firebase.auth.FirebaseAuth

@Composable
fun BottomNavBar(navController: NavController) {
    BottomAppBar(Modifier.background(MaterialTheme.colorScheme.primary)) {
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.PlaylistPlay, contentDescription = "Playlists") },
            label = { Text("Playlists") },
            selected = false,
            onClick = {
                navController.navigate("playlists") {
                    popUpTo("playlists") { inclusive = true }
                }
            }
        )


        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.ManageSearch, contentDescription = "Playlists") },
            label = { Text("Search") },
            selected = false,
            onClick = {
//                navController.navigate("playlists") {
//                    popUpTo("playlists") { inclusive = true }
//                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.AirplaneTicket, contentDescription = "Playlists") },
            label = { Text("Genre") },
            selected = false,
            onClick = {
//                navController.navigate("playlists") {
//                    popUpTo("playlists") { inclusive = true }
//                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.Chat, contentDescription = "Playlists") },
            label = { Text("Chat") },
            selected = false,
            onClick = {
//                navController.navigate("playlists") {
//                    popUpTo("playlists") { inclusive = true }
//                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.BrandingWatermark, contentDescription = "Playlists") },
            label = { Text("Radio") },
            selected = false,
            onClick = {
//                navController.navigate("playlists") {
//                    popUpTo("playlists") { inclusive = true }
//                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Logout") },
            label = { Text("Logout") },
            selected = false,
            onClick = {
                AuthManager.signOut()
                navController.navigate("login") {
                    popUpTo("login") { inclusive = true }
                }
            }
        )
    }
}