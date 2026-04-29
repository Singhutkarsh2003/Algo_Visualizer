package com.example.algovisualizer.domain.usecase.algorithm

import com.example.algovisualizer.domain.model.Step
import com.example.algovisualizer.domain.repository.AlgoRepository

class GetStepsUseCase (
    private  val repository: AlgoRepository
){
    suspend operator  fun invoke(algoId: Int): List<Step>{
        return repository.getSteps(algoId)
    }
}