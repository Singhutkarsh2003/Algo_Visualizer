package com.example.algovisualizer.data.remote.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreService {
    private  val db  = FirebaseFirestore.getInstance()

    suspend fun  saveUser(userId: String, data: Map<String, Any>){
        db.collection("users").document(userId).set(data).await()
    }
}