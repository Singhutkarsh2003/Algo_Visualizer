package com.example.algovisualizer.domain.usecase.dsa.searching

import com.example.algovisualizer.domain.model.SearchState
import kotlinx.coroutines.delay

class LinearSearch {

    suspend fun search(
        list: List<Int>,
        target: Int,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (SearchState) -> Unit
    ) {

        for (i in list.indices) {

            while (isPaused()) delay(100)

            onStep(
                SearchState(
                    number = list,
                    activeIndices = Pair(i, -1),
                    message = "Checking ${list[i]}"
                )
            )

            delay(delayTime())

            if (list[i] == target) {
                onStep(
                    SearchState(
                        number = list,
                        foundIndex = i,
                        message = "Found $target at index $i"
                    )
                )
                return
            }
        }

        onStep(
            SearchState(
                number = list,
                message = "$target Not Found"
            )
        )
    }
}