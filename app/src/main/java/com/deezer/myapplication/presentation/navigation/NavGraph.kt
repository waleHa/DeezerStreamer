package com.deezer.myapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.deezer.myapplication.presentation.compose.loginregister.LoginScreen
import com.deezer.myapplication.presentation.compose.loginregister.RegistrationScreen
import com.deezer.myapplication.presentation.compose.user.playlist.PlaylistScreen
import com.deezer.myapplication.presentation.compose.user.track.TrackScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegistrationScreen(navController) }
        composable("playlists") { PlaylistScreen(navController) }
        composable("tracks/{playlistId}") { backStackEntry ->
            TrackScreen(playlistId = backStackEntry.arguments?.getString("playlistId") ?: "")
        }
    }
}
