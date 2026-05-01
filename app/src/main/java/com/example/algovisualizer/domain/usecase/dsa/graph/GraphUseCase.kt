package com.example.algovisualizer.domain.usecase.dsa.graph

import com.example.algovisualizer.domain.model.Edge
import kotlinx.coroutines.delay

class Dijkstra {

    suspend fun run(
        graph: Map<Int, List<Edge>>,
        start: Int,
        delayTime: () -> Long,
        isPaused: () -> Boolean,
        onStep: (Set<Int>, List<Int>) -> Unit
    ) {

        val dist = mutableMapOf<Int, Int>()
        val visited = mutableSetOf<Int>()
        val parent = mutableMapOf<Int, Int>()

        graph.keys.forEach { dist[it] = Int.MAX_VALUE }
        dist[start] = 0

        repeat(graph.size) {

            while (isPaused()) delay(100)

            val u = dist
                .filter { !visited.contains(it.key) }
                .minByOrNull { it.value }?.key ?: return

            visited.add(u)

            graph[u]?.forEach { edge ->
                val newDist = dist[u]!! + edge.weight

                if (newDist < dist[edge.to]!!) {
                    dist[edge.to] = newDist
                    parent[edge.to] = u
                }
            }

            val path = buildPath(parent, start, u)

            onStep(visited.toSet(), path)

            delay(delayTime())
        }
    }

    private fun buildPath(parent: Map<Int, Int>, start: Int, end: Int): List<Int> {
        val path = mutableListOf<Int>()
        var curr = end

        while (parent.containsKey(curr)) {
            path.add(curr)
            curr = parent[curr]!!
        }

        path.add(start)
        return path.reversed()
    }
}