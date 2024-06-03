package com.deezer.myapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.deezer.myapplication.presentation.navigation.BottomNavBar
import com.deezer.myapplication.presentation.navigation.NavGraph
import com.deezer.myapplication.presentation.theme.DeezerStreamerTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.Modifier
import com.deezer.core.AuthManager

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DeezerStreamerTheme {
                val navController = rememberNavController()
                LaunchedEffect(Unit) {
                    checkCurrentUser(navController)
                }
                MainApp(navController = navController)
            }
        }
    }

    private fun checkCurrentUser(navController: NavHostController) {
        if (AuthManager.getCurrentUser() != null) {
            navController.navigate("playlists") {
                popUpTo("login") { inclusive = true }
            }
        }
    }
}

@Composable
fun MainApp(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavGraph(navController = navController)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DeezerStreamerTheme {
//        val navController = rememberNavController()
//        NavGraph(navController = navController)
    }
}