package com.example.algovisualizer.domain.usecase.auth

import com.example.algovisualizer.domain.repository.AuthRepository

class RegisterUseCase (
    private  val repository: AuthRepository
){
    suspend  operator fun invoke(name: String, email: String, password: String): Boolean{
        return  repository.register(name, email, password)
    }
}