package com.example.algovisualizer.domain.model

data class ArrayState(
   val number : List<Int>,
    val activeIndices: Pair<Int, Int> ?= null,
    val foundIndex: Int? = null
)