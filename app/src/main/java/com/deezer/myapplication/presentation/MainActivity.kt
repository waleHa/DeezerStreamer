package com.deezer.myapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.compose.currentBackStackEntryAsState
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
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val listOfInitialScreens = listOf("login", "register")
    val showTopBar = currentRoute !in listOfInitialScreens
    val showBottomBar = currentRoute !in listOfInitialScreens

    Scaffold(
        topBar = { if (showTopBar) AppBar(navController) },
        bottomBar = { if (showBottomBar) BottomNavBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavGraph(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }


    TopAppBar(
        title = { Text(text = "Deezer Streamer") },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Menu")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        AuthManager.signOut()
                        navController.navigate("login") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    text = { Text("Logout") }
                )
            }
        }
    )
}

