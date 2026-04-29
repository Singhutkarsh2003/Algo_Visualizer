package com.example.algovisualizer.domain.usecase.auth

import com.example.algovisualizer.domain.repository.AuthRepository

class LoginUseCase (
    private  val repository: AuthRepository
){
    suspend operator  fun invoke(email: String , password: String): Boolean{
        return  repository.login(email, password)
    }
}