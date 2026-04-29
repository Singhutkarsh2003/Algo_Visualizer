package com.example.algovisualizer.domain.model

data class SearchState(
    val number : List<Int> = emptyList(),
    val activeIndices : Pair<Int,Int>? = null,
    val foundIndex : Int? = null,
    val message : String = ""
)
