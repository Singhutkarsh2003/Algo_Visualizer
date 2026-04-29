package com.example.algovisualizer.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algovisualizer.domain.usecase.auth.LoginUseCase
import com.example.algovisualizer.domain.usecase.auth.RegisterUseCase
import com.example.algovisualizer.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
) : ViewModel(){

    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Idle)
    val uiState: StateFlow<UiState<String>> = _uiState

    fun login(email: String, password: String){

        viewModelScope.launch {

            _uiState.value = UiState.Loading

            try {
                loginUseCase(email, password)
                _uiState.value = UiState.Success("Login Success")
            }
            catch (e: Exception){
                _uiState.value = UiState.Error(e.message ?: "Login Failed")
            }

        }
    }

    fun register( email: String, password: String){
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            try {
                loginUseCase(email, password)
                _uiState.value = UiState.Success("Registered Successfully")
            }
            catch (e: Exception){
                _uiState.value = UiState.Error(e.message ?:"Error")
            }

        }
    }

}