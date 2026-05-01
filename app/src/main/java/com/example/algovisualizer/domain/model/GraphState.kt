package com.example.algovisualizer.domain.model

data class Edge(
    val to: Int,
    val weight: Int
)

data class GraphNode(
    val id: Int,
    val x: Float,
    val y: Float
)