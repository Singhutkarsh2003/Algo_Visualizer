package com.example.algovisualizer.domain.usecase.algorithm

import com.example.algovisualizer.domain.model.Step
import com.example.algovisualizer.domain.repository.AlgoRepository

class InsertStepUseCase(
    private val repository: AlgoRepository
) {
    suspend operator fun invoke(step: List<Step>) {
        repository.insertStep(step)
    }

}