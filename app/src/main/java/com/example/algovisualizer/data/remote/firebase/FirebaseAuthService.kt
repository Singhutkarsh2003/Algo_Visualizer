package com.example.algovisualizer.data.remote.firebase

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthService {
    private  val auth = FirebaseAuth.getInstance()

    suspend fun login(email: String, password: String): Boolean{
        return  try {
            auth.signInWithEmailAndPassword(email, password).await()
            true
        }catch (e: Exception){
            false
        }
    }

    suspend fun register(name: String, email: String, password: String): Boolean{
        return  try {
            auth.createUserWithEmailAndPassword(email, password).await()
            true
        }catch (e: Exception){
            false
        }
    }

    fun logout(){
        auth.signOut()
    }
    fun getCurrentUser()= auth.currentUser

}