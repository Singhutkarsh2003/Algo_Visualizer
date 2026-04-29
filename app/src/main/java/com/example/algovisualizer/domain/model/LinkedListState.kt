package com.example.algovisualizer.domain.model

data class LinkedListState(
    val numbers: List<Int>,
    val activeIndex : Int? = null,
    val message : String = ""
)
