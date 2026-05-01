package com.example.algovisualizer.presentation.visualizer.graph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algovisualizer.domain.model.Edge
import com.example.algovisualizer.domain.model.GraphNode
import com.example.algovisualizer.domain.usecase.dsa.graph.Dijkstra
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GraphViewModel : ViewModel() {

    private val _visited = MutableStateFlow<Set<Int>>(emptySet())
    val visited: StateFlow<Set<Int>> = _visited

    private val _path = MutableStateFlow<List<Int>>(emptyList())
    val path: StateFlow<List<Int>> = _path

    private val dijkstra = Dijkstra()

    private var isPaused = false
    private var speed = 500L

    val graph = mapOf(
        0 to listOf(Edge(1, 4), Edge(2, 1)),
        1 to listOf(Edge(3, 1)),
        2 to listOf(Edge(1, 2), Edge(3, 5)),
        3 to emptyList()
    )

    val nodes = listOf(
        GraphNode(0, 100f, 100f),
        GraphNode(1, 300f, 100f),
        GraphNode(2, 200f, 300f),
        GraphNode(3, 300f, 500f)
    )

    fun start() {
        viewModelScope.launch {
            dijkstra.run(
                graph,
                start = 0,
                delayTime = { speed },
                isPaused = { isPaused }
            ) { visitedSet, pathList ->
                _visited.value = visitedSet
                _path.value = pathList
            }
        }
    }

    fun togglePause() {
        isPaused = !isPaused
    }

    fun setSpeed(value: Float) {
        speed = value.toLong()
    }
}