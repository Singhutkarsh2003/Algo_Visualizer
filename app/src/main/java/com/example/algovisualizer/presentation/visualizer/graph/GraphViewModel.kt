package com.example.algovisualizer.presentation.visualizer.graph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algovisualizer.domain.model.GraphState
import com.example.algovisualizer.domain.usecase.dsa.graph.GraphUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GraphViewModel : ViewModel() {

    private val useCase = GraphUseCase()

    private val graph = mapOf(
        1 to listOf(2, 3),
        2 to listOf(4, 5),
        3 to listOf(6),
        4 to emptyList(),
        5 to emptyList(),
        6 to emptyList()
    )

    private val _state = MutableStateFlow(GraphState(message = "Initial Graph"))
    val state: StateFlow<GraphState> = _state

    fun bfs() {
        viewModelScope.launch {
            useCase.bfs(graph, 1, { 1500 }, { false }) {
                _state.value = it
            }
        }
    }
}