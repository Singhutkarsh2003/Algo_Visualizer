package com.example.algovisualizer.data.repository

import com.example.algovisualizer.data.remote.firebase.FirebaseAuthService
import com.example.algovisualizer.domain.repository.AuthRepository

class AuthRepositoryImpl (
    private val firebaseAuthService : FirebaseAuthService
) : AuthRepository{

    override suspend fun login(email: String, password: String): Boolean {
        return firebaseAuthService.login(email, password)
    }

    override suspend fun register(name: String, email: String, password: String): Boolean {
        return firebaseAuthService.register(name, email, password)
    }

    override fun logout() {
        firebaseAuthService.logout()
    }

}