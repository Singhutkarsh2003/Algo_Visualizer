package com.example.algovisualizer.domain.usecase.auth

import com.example.algovisualizer.domain.repository.AuthRepository

class LogoutUseCase (
    private val repository : AuthRepository
){
    operator  fun invoke(){
        repository.logout()
    }
}