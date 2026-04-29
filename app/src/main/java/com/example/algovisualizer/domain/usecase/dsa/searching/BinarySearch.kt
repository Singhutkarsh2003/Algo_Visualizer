package com.example.algovisualizer.domain.usecase.dsa.searching

import com.example.algovisualizer.domain.model.SearchState
import kotlinx.coroutines.delay

class BinarySearch {

    suspend fun search(
        list: List<Int>,
        target: Int,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (SearchState) -> Unit
    ) {

        val sortedList = list.sorted()
        var low = 0
        var high = sortedList.size - 1

        while (low <= high) {

            while (isPaused()) delay(100)

            val mid = (low + high) / 2

            onStep(
                SearchState(
                    number = sortedList,
                    activeIndices = Pair(low, high),
                    message = "Checking mid index $mid"
                )
            )

            delay(delayTime())

            when {
                sortedList[mid] == target -> {
                    onStep(
                        SearchState(
                            number = sortedList,
                            foundIndex = mid,
                            message = "Found $target at index $mid"
                        )
                    )
                    return
                }

                sortedList[mid] < target -> low = mid + 1
                else -> high = mid - 1
            }
        }

        onStep(
            SearchState(
                number = sortedList,
                message = "$target Not Found"
            )
        )
    }
}