package com.example.algovisualizer.domain.usecase.dsa.sorting

import com.example.algovisualizer.domain.model.ArrayState
import kotlinx.coroutines.delay

class MergeSort {

    suspend fun sort (
        list: List<Int>,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep : suspend (ArrayState) -> Unit
    ){

        val list = list.toMutableList()
        mergeSort(list , 0 , list.size-1 ,delayTime, isPaused, onStep)
        onStep(ArrayState(list.toList()))

    }
    private  suspend fun  mergeSort(
        arr: MutableList<Int>,
        left: Int,
        right: Int,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep:  suspend (ArrayState) -> Unit
    ){
        if (left >= right) return

        val mid = (left+right)/2
        mergeSort(arr , left , mid ,delayTime, isPaused, onStep)
        mergeSort(arr , mid+1 , right ,delayTime, isPaused, onStep)
        merge(arr , left , mid , right ,delayTime,isPaused, onStep)

    }


    private  suspend fun merge(
        arr: MutableList<Int>,
        left: Int,
        mid: Int,
        right: Int,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: suspend (ArrayState) -> Unit
    ){
        val leftPart = arr.subList(left, mid+1).toList()
        val rightPart = arr.subList(mid+1, right+1).toList()

        var i=0
        var j=0
        var k = left

        while (i < leftPart.size && j < rightPart.size){
            while (isPaused()) delay(100)

            if (leftPart[i] <= rightPart[j]){
                arr[k] = leftPart[i]
                i++
            }else{
                arr[k] = rightPart[j]
                j++
            }
            onStep(ArrayState(number = arr.toList()))
            delay(100)
            k++
        }
        while (i< leftPart.size){
            arr[k] = leftPart[i]
            i++
            k++

            onStep(ArrayState(number = arr.toList()))
            delay(delayTime())

        }
        while (j< rightPart.size){

            arr[k] = rightPart[j]
            j++
            k++

            onStep(ArrayState(number = arr.toList()))
            delay(delayTime())

        }

    }

}