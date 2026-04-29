package com.example.algovisualizer.domain.usecase.algorithm

import com.example.algovisualizer.domain.repository.ProgressRepository

class SaveProgressUseCase (
    private  val repository: ProgressRepository
){
    suspend operator  fun invoke(userId: String, algoId: Int, progress: Int){
        repository.saveProgress(userId, algoId, progress)
    }
}