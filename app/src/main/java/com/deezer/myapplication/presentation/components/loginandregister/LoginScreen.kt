package com.deezer.myapplication.presentation.components.loginandregister


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.deezer.myapplication.R
import com.deezer.myapplication.presentation.theme.DeezerStreamerTheme
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }
    val (loginError, setLoginError) = remember { mutableStateOf<String?>(null) }

    val isFormValid = email.isNotBlank() && password.isNotBlank()
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(250.dp)
                .padding(bottom = 16.dp),
            painter = painterResource(id = R.drawable.deezer_logo), contentDescription = null)
        Text("Hey there,", style = MaterialTheme.typography.headlineSmall)
        Text(
            "Welcome Back",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = setEmail,
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = setPassword,
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
            trailingIcon = {
                IconButton(onClick = { setPasswordVisible(!passwordVisible) }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        loginError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {}) {
            Text("Forgot your password?")
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                isVerified(email, password, auth, navController, setLoginError)
            },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(modifier = Modifier.weight(1f))
            Text("Or", modifier = Modifier.padding(8.dp))
            Divider(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { navController.navigate("register") }) {
            Text("Don't have an account yet? Register")
        }
    }
}

private fun isVerified(
    email: String,
    password: String,
    auth: FirebaseAuth,
    navController: NavController,
    setLoginError: (String?) -> Unit
) {
    if (email.isNotBlank() && password.isNotBlank()) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navController.navigate("playlists")
                } else {
                    setLoginError(task.exception?.message)
                }
            }
    } else {
        setLoginError("Email and Password cannot be empty")
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    DeezerStreamerTheme {
        LoginScreen(navController = rememberNavController())
    }
}
