package com.example.algovisualizer.domain.usecase.algorithm

data class AlgoUseCases (
    val getAlgorithms: GetAlgorithmsUseCase,
    val getSteps: GetStepsUseCase,
    val insertAlgorithm: InsertAlgorithmUseCase,
    val insertStep: InsertStepUseCase
)