package com.example.algovisualizer.domain.usecase.dsa.sorting

import com.example.algovisualizer.domain.model.ArrayState
import kotlinx.coroutines.delay

class BubbleSort {

    suspend fun  sort(
        list: List<Int>,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: suspend (ArrayState) -> Unit
    ){
        val arr = list.toMutableList()

        for (i in arr.indices){
            for (j in 0 until arr.size-i-1){

                while (isPaused()){
                    delay(100)
                }

                onStep(ArrayState(arr.toList(), Pair(j, j+1)))

                delay(delayTime())

                if (arr[j] > arr[j+1]){
                    val temp = arr[j]
                    arr[j] = arr[j+1]
                    arr[j+1] = temp

                    onStep(ArrayState(arr.toList(), Pair(j, j+1)))

                    delay(delayTime())

                }

            }
        }
        onStep(ArrayState(arr.toList()))

    }

}