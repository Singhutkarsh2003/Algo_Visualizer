package com.example.algovisualizer.domain.usecase.dsa.sorting

import com.example.algovisualizer.domain.model.ArrayState
import kotlinx.coroutines.delay

class SelectionSort {

    suspend fun  sort(
        list: List<Int>,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: suspend (ArrayState) -> Unit
    ) {
        val arr = list.toMutableList()

        for (i in arr.indices){

            var minIndex = i

            for (j in i+1 until arr.size){
                 while (isPaused() ){
                     delay(100)
                 }

                onStep(ArrayState(arr.toList(), Pair(minIndex , j)))
                delay(delayTime())

                if(arr[j] < arr[minIndex]){
                    minIndex = j
                }
            }
            if(i != minIndex){
                val temp = arr[i]
                arr[i] = arr[minIndex]
                arr[minIndex] = temp

                onStep(ArrayState(arr.toList(), Pair(i, minIndex)))
                delay(delayTime())
            }
        }
        onStep(ArrayState(arr.toList()))
    }

}