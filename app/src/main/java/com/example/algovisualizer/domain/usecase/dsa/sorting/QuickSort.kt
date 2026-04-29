package com.example.algovisualizer.domain.usecase.dsa.sorting

import com.example.algovisualizer.domain.model.ArrayState
import kotlinx.coroutines.delay

class QuickSort {

    suspend fun  sort(
        list: List<Int>,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (ArrayState) -> Unit
    ){
        val list = list.toMutableList()
        quickSort(list , 0 , list.size-1 ,delayTime, isPaused, onStep)
    }

    private suspend fun  quickSort(
        arr: MutableList<Int>,
        low : Int,
        high: Int,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (ArrayState) -> Unit
    ){
        if(low<high){
            val pivotIndex = partition(arr , low , high ,delayTime, isPaused, onStep)
            quickSort(arr , low , pivotIndex-1 ,delayTime,isPaused, onStep)
            quickSort(arr , pivotIndex+1 , high ,delayTime, isPaused, onStep)

        }
    }
    private  suspend fun  partition(
        arr: MutableList<Int>,
        low: Int,
        high: Int,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (ArrayState) -> Unit
    ): Int{
        val pivot = arr[high]
        var i = low-1

        for (j in low until high){

            while (isPaused()) delay(100)

            onStep(ArrayState(arr.toList(), Pair(j,high)))
            delay(delayTime())

            if(arr[j]<pivot){
                i++
                swap(arr, i, j)

                onStep(ArrayState(number = arr.toList()))
                delay(delayTime())

            }
        }
        swap(arr, i+1 , high)

        onStep(ArrayState(number = arr.toList()))
        delay(delayTime())

        return i+1
    }
    private  suspend fun  swap(arr: MutableList<Int>, i: Int, j: Int){
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }

}