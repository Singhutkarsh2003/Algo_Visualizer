package com.example.algovisualizer.domain.repository

import com.example.algovisualizer.domain.model.Algorithm
import com.example.algovisualizer.domain.model.Step

interface  AlgoRepository{

    suspend fun getAlgorithms() : List<Algorithm>

    suspend fun getSteps(algoId: Int): List<Step>

    suspend fun insertAlgorithm(algorithm: Algorithm)

    suspend fun insertStep(step: List<Step>)
}