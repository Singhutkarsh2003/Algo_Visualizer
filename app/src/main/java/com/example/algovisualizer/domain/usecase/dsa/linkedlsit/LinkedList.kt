package com.example.algovisualizer.domain.usecase.dsa.linkedlsit

import com.example.algovisualizer.domain.model.LinkedListState
import kotlinx.coroutines.delay
class LinkedList {

    // INSERT
    suspend fun insert(
        list: MutableList<Int>,
        value: Int,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (LinkedListState) -> Unit
    ) {
        while (isPaused()) delay(100)

        list.add(value)

        onStep(
            LinkedListState(
                numbers = list.toList(),
                message = "Inserted $value"
            )
        )
        delay(delayTime())
    }

    // DELETE  FIXED
    suspend fun delete(
        list: MutableList<Int>,
        value: Int,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (LinkedListState) -> Unit
    ) {
        for (i in list.indices) {

            while (isPaused()) delay(100)

            onStep(
                LinkedListState(
                    numbers = list.toList(),
                    activeIndex = i,
                    message = "Checking ${list[i]}"
                )
            )
            delay(delayTime())

            if (list[i] == value) {

                list.removeAt(i)   //  ACTUAL DELETE

                onStep(
                    LinkedListState(
                        numbers = list.toList(),
                        message = "Deleted $value"
                    )
                )
                return
            }
        }

        onStep(
            LinkedListState(
                numbers = list.toList(),
                message = "Value not found"
            )
        )
    }

    // SEARCH  NEW (this was missing → your error)
    suspend fun search(
        list: List<Int>,
        value: Int,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (LinkedListState) -> Unit
    ) {
        for (i in list.indices) {

            while (isPaused()) delay(100)

            onStep(
                LinkedListState(
                    numbers = list,
                    activeIndex = i,
                    message = "Checking ${list[i]}"
                )
            )
            delay(delayTime())

            if (list[i] == value) {
                onStep(
                    LinkedListState(
                        numbers = list,
                        activeIndex = i,
                        message = "Found $value at index $i"
                    )
                )
                return
            }
        }

        onStep(
            LinkedListState(
                numbers = list,
                message = "Not Found"
            )
        )
    }
}