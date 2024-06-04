package com.deezer.myapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.deezer.core.Constant
import com.deezer.myapplication.presentation.compose.loginregister.LoginScreen
import com.deezer.myapplication.presentation.compose.loginregister.RegistrationScreen
import com.deezer.myapplication.presentation.compose.album.AlbumDetailScreen
import com.deezer.myapplication.presentation.compose.podcast.PodcastDetailScreen
import com.deezer.myapplication.presentation.compose.podcast.PodcastScreen
import com.deezer.myapplication.presentation.compose.radio.RadioScreen
import com.deezer.myapplication.presentation.compose.artist.ArtistDetailScreen
import com.deezer.myapplication.presentation.compose.track.search.SearchItemDetailOptions
import com.deezer.myapplication.presentation.compose.track.search.SearchTrackScreen
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

        composable("search") {
            SearchTrackScreen(navController = navController, modifier = Modifier)
        }

        composable("searchItem/{searchItemId}") { backStackEntry ->
            val searchItemId = backStackEntry.arguments?.getString("searchItemId") ?: ""
            SearchItemDetailOptions(searchItemId = searchItemId, navController = navController)
        }
        composable("artist/{artistId}") { backStackEntry ->
            val artistId = backStackEntry.arguments?.getString("artistId") ?: Constant.ARTIST_DEFAULT
            ArtistDetailScreen(artistId = artistId)
        }
        composable("album/{albumId}") { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId") ?: Constant.ALBUM_DEFAULT
            AlbumDetailScreen(albumId = albumId)
        }

        composable("podcasts") {
            PodcastScreen(navController = navController)
        }
        composable("podcastDetail/{podcastId}") { backStackEntry ->
            val podcastId = backStackEntry.arguments?.getString("podcastId") ?: ""
            PodcastDetailScreen(podcastId = podcastId)
        }
        composable("radio") {
            RadioScreen(navController = navController)
        }
    }
}
