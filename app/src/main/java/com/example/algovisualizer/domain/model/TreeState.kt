package com.example.algovisualizer.domain.model

data class TreeState(
    val nodes : List<Int> = emptyList(),
    val visited : List<Int> = emptyList(),
    val current : Int? = null,
    val message : String = ""
)