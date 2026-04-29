package com.example.algovisualizer.domain.model

data class GraphState(
    val visited: List<Int> = emptyList(),
    val current: Int? = null,
    val message: String = ""

)