package com.example.algovisualizer.data.repository

import com.example.algovisualizer.data.local.dao.UserProgressDao
import com.example.algovisualizer.data.local.entity.UserProgressEntity
import com.example.algovisualizer.domain.repository.ProgressRepository

class ProgressRepositoryImpl(
    private  val dao: UserProgressDao
) : ProgressRepository{

    override suspend fun saveProgress(userId: String, algoId: Int, progress: Int) {
        dao.saveProgress(
            UserProgressEntity(
                userId = userId,
                algorithmId = algoId,
                progress = progress
            )
        )
    }

    override suspend fun getProgress(userId: String): List<Pair<Int, Int>> {
        return dao.getUserProgress(userId).map { it.algorithmId to it.progress }
    }

}