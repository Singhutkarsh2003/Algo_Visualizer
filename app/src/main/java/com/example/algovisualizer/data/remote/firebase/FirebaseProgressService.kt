package com.example.algovisualizer.data.remote.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseProgressService {
    private val db = FirebaseFirestore.getInstance()

    suspend fun  saveProgress(userId: String, algoId: Int, progress: Int){
        val data = mapOf(
            "algoId" to algoId,
            "progress" to progress
        )
        db.collection("users")
            .document(userId)
            .collection("algorithms")
            .document(algoId.toString())
            .set(data)
            .await()
    }
}