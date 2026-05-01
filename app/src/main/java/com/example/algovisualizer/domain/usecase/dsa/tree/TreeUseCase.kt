package com.example.algovisualizer.domain.usecase.dsa.tree

import com.example.algovisualizer.domain.model.TreeNode
import com.example.algovisualizer.domain.model.TreeState
import kotlinx.coroutines.delay

class TreeUseCase {

    //  DFS Traversal
    suspend fun dfs(
        root: TreeNode?,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (TreeState) -> Unit
    ) {
        val visited = mutableListOf<Int>()
        dfsHelper(root, visited, delayTime, isPaused, onStep)
    }

    private suspend fun dfsHelper(
        node: TreeNode?,
        visited: MutableList<Int>,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (TreeState) -> Unit
    ) {
        if (node == null) return

        while (isPaused()) delay(100)

        visited.add(node.value)

        onStep(
            TreeState(
                root = node,
                visited = visited.toList(),
                message = "Visited ${node.value}"
            )
        )

        delay(delayTime())

        dfsHelper(node.left, visited, delayTime, isPaused, onStep)
        dfsHelper(node.right, visited, delayTime, isPaused, onStep)
    }

    //  BFS Traversal
    suspend fun bfs(
        root: TreeNode?,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (TreeState) -> Unit
    ) {
        val queue = ArrayDeque<TreeNode?>()
        val visited = mutableListOf<Int>()

        queue.add(root)

        while (queue.isNotEmpty()) {

            while (isPaused()) delay(100)

            val node = queue.removeFirst() ?: continue

            visited.add(node.value)

            onStep(
                TreeState(
                    root = root,
                    visited = visited.toList(),
                    message = "Visited ${node.value}"
                )
            )

            delay(delayTime())

            queue.add(node.left)
            queue.add(node.right)
        }
    }
}