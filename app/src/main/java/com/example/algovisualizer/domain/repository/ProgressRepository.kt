package com.example.algovisualizer.domain.repository

interface ProgressRepository{

    suspend fun saveProgress(userId: String, algoId: Int, progress: Int)

    suspend fun  getProgress(userId: String): List<Pair<Int, Int>>
}