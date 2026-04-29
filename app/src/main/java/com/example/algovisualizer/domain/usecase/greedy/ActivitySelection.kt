package com.example.algovisualizer.domain.usecase.greedy

import com.example.algovisualizer.domain.model.Activity
import com.example.algovisualizer.domain.model.GreedyState
import kotlinx.coroutines.delay

class ActivitySelection {

    suspend fun selectActivities(
        activities: List<Activity>,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (GreedyState) -> Unit
    ) {

        if (activities.isEmpty()) return

        val sorted = activities.sortedBy { it.end }
        val selected = mutableListOf<Activity>()

        // Step 1: Select first activity
        selected.add(sorted[0])

        onStep(
            GreedyState(
                activities = sorted,
                selected = selected.toList(),
                message = "Select first: ${sorted[0].start}-${sorted[0].end}"
            )
        )

        delayControl(delayTime, isPaused)

        var lastEnd = sorted[0].end

        // Step 2: Check others
        for (i in 1 until sorted.size) {

            val current = sorted[i]

            onStep(
                GreedyState(
                    activities = sorted,
                    selected = selected.toList(),
                    message = "Checking ${current.start}-${current.end}"
                )
            )

            delayControl(delayTime, isPaused)

            if (current.start >= lastEnd) {

                selected.add(current)
                lastEnd = current.end

                onStep(
                    GreedyState(
                        activities = sorted,
                        selected = selected.toList(),
                        message = "Selected ${current.start}-${current.end}"
                    )
                )

                delayControl(delayTime, isPaused)
            }
        }

        onStep(
            GreedyState(
                activities = sorted,
                selected = selected.toList(),
                message = "Completed"
            )
        )
    }

    private suspend fun delayControl(
        delayTime: () -> Long,
        isPaused: () -> Boolean
    ) {
        while (isPaused()) delay(100)
        delay(delayTime())
    }
}