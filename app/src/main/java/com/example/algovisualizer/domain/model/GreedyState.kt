package com.example.algovisualizer.domain.model

data class Activity(
    val start: Int,
    val end: Int
)

data class GreedyState(
    val activities: List<Activity> = emptyList(),
    val selected: List<Activity> = emptyList(),
    val message: String = ""
)
