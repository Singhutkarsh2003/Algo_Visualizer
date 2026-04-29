package com.example.algovisualizer.domain.usecase.dsa.sorting

import com.example.algovisualizer.domain.model.ArrayState
import kotlinx.coroutines.delay

class InsertionSort {

    suspend fun  sort(
        list: List<Int>,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onstep: suspend (ArrayState) -> Unit
    ){
        val arr = list.toMutableList()
        for (i in 1 until arr.size){
            val key = arr[i]
            var j = i-1

            while(j >=0 && arr[j]> key){
                while (isPaused()){
                    delay(100)
                }
                arr[j+1] = arr[j]

                onstep(ArrayState(arr.toList(), Pair(j+1, i)))
                delay(delayTime())

                j--
            }
            arr[j+1]= key
            onstep(ArrayState(arr.toList(), Pair(j+1, i)))
            delay(delayTime())
        }
        onstep(ArrayState(arr.toList()))
    }
}