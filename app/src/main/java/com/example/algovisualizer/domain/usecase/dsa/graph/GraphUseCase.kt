package com.example.algovisualizer.domain.usecase.dsa.graph

import com.example.algovisualizer.domain.model.GraphState
import kotlinx.coroutines.delay
import java.util.LinkedList
import java.util.Queue

class GraphUseCase {

    suspend fun bfs(
        graph: Map<Int, List<Int>>,
        start: Int,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (GraphState) -> Unit
    ) {
        val queue: Queue<Int> = LinkedList()
        val visited = mutableListOf<Int>()

        queue.add(start)

        while (queue.isNotEmpty()) {

            while (isPaused()) delay(50)

            val node = queue.poll()

            if (node !in visited) {
                visited.add(node)

                onStep(
                    GraphState(
                        visited = visited.toList(),
                        current = node,
                        message = "Visiting $node"
                    )
                )

                delay(delayTime())

                graph[node]?.forEach { queue.add(it) }
            }
        }

        onStep(GraphState(visited, message = "Traversal Complete"))
    }
}