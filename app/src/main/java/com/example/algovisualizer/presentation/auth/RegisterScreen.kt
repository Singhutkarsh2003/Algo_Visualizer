package com.example.algovisualizer.presentation.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.algovisualizer.presentation.common.UiState

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    onSuccess:() -> Unit,
    onGoTOLogin:() -> Unit
){

    val state by viewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text(
            "Register", style = MaterialTheme.typography.headlineMedium
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Button(onClick = {
            viewModel.register(email, password)
        }) {
            Text("Register")
        }

        TextButton(onClick = onGoTOLogin) {
            Text("Already have an account? Login")
        }

        when(state){
            is UiState.Loading -> Text(text = "Loading")
            is UiState.Success -> onSuccess()
            is UiState.Error -> Text(text = (state as UiState.Error).message)
            else -> {}

        }

    }

}