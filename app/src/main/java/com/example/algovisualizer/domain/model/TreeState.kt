package com.example.algovisualizer.domain.model

data class TreeState(
    val root: TreeNode? = null,
    val visited: List<Int> = emptyList(),
    val message: String = ""
)