package com.example.algovisualizer.domain.model

data class Step(
    val id: Int,
    val algorithmId: Int,
    val stepNumber: Int,
    val explanation: String,
    val state: List<Int>
)