package com.example.algovisualizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.algovisualizer.data.remote.firebase.FirebaseAuthService
import com.example.algovisualizer.data.repository.AuthRepositoryImpl
import com.example.algovisualizer.domain.usecase.auth.LoginUseCase
import com.example.algovisualizer.domain.usecase.auth.RegisterUseCase
import com.example.algovisualizer.presentation.auth.AuthViewModel
import com.example.algovisualizer.presentation.navigation.NavGraph
import com.example.algovisualizer.ui.theme.AlgoVisualizerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repository = AuthRepositoryImpl(FirebaseAuthService())

        val viewModel = AuthViewModel(
            registerUseCase = RegisterUseCase(repository),
            loginUseCase = LoginUseCase(repository)
        )

        setContent {
            AlgoVisualizerTheme {
                val navController = rememberNavController()
                NavGraph( navController = navController, authViewModel = viewModel)
            }
        }
    }
}

