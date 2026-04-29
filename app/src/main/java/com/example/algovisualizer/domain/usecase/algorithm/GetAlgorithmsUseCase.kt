package com.example.algovisualizer.domain.usecase.algorithm

import com.example.algovisualizer.domain.model.Algorithm
import com.example.algovisualizer.domain.repository.AlgoRepository

class  GetAlgorithmsUseCase(
    private  val repository: AlgoRepository
){
    suspend  operator  fun  invoke(): List<Algorithm>{
        return repository.getAlgorithms()
    }
}