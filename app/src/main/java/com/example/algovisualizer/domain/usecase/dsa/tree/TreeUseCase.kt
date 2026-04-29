package com.example.algovisualizer.domain.usecase.dsa.tree

import com.example.algovisualizer.domain.model.TreeState
import kotlinx.coroutines.delay

class TreeUseCase {

    suspend fun inorder(
        list: List<Int>,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (TreeState) -> Unit
    ) {
        val visited = mutableListOf<Int>()

        suspend fun traverse(index: Int) {
            if (index >= list.size) return

            while (isPaused()) delay(100)

            traverse(2 * index + 1)

            visited.add(list[index])

            onStep(
                TreeState(
                    nodes = list,
                    visited = visited.toList(),
                    current = list[index],
                    message = "Visiting ${list[index]}"
                )
            )

            delay(delayTime())

            traverse(2 * index + 2)
        }

        traverse(0)

        onStep(
            TreeState(
                nodes = list,
                visited = visited,
                message = "Traversal Complete"
            )
        )
    }
}