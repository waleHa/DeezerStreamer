package com.deezer.myapplication.presentation.components.loginandregister
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.deezer.myapplication.R
import com.deezer.myapplication.presentation.theme.DeezerStreamerTheme
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegistrationScreen(navController: NavController) {
    val (firstName, setFirstName) = remember { mutableStateOf("") }
    val (lastName, setLastName) = remember { mutableStateOf("") }
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }
    val (termsAccepted, setTermsAccepted) = remember { mutableStateOf(false) }
    val (registrationError, setRegistrationError) = remember { mutableStateOf<String?>(null) }


    val isFormValid = firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && password.isNotBlank() && termsAccepted
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hey there,", style = MaterialTheme.typography.headlineMedium)
        Text("Create an Account", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = firstName,
            onValueChange = setFirstName,
            label = { Text("First Name") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = setLastName,
            label = { Text("Last Name") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

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
                    Icon(if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility, contentDescription = "Toggle Password Visibility")
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = termsAccepted, onCheckedChange = setTermsAccepted)
            Text("By continuing you accept our ", style = MaterialTheme.typography.bodyMedium)
            TextButton(onClick = { /* Handle privacy policy click */ }) { Text("Privacy Policy & Terms") }
        }
        Spacer(modifier = Modifier.height(24.dp))

        registrationError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                handleRegistration(
                    email = email,
                    password = password,
                    firstName = firstName,
                    lastName = lastName,
                    auth = auth,
                    navController = navController,
                    setRegistrationError = setRegistrationError
                )
            },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
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

        TextButton(onClick = { navController.navigate("login") }) {
            Text("Already have an account? Login")
        }
    }
}

private fun handleRegistration(
    email: String,
    password: String,
    firstName: String,
    lastName: String,
    auth: FirebaseAuth,
    navController: NavController,
    setRegistrationError: (String?) -> Unit
) {
    if (email.isNotBlank() && password.isNotBlank() && firstName.isNotBlank() && lastName.isNotBlank()) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navController.navigate("playlists")
                } else {
                    setRegistrationError(task.exception?.message)
                }
            }
    } else {
        setRegistrationError("All fields must be filled and terms accepted.")
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    DeezerStreamerTheme {
        RegistrationScreen(navController = rememberNavController())
    }
}
