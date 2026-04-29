package com.example.algovisualizer.presentation.visualizer.tree

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algovisualizer.domain.model.TreeState
import com.example.algovisualizer.domain.usecase.dsa.tree.TreeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TreeViewModel : ViewModel() {

    private val useCase = TreeUseCase()

    private val _state = MutableStateFlow(
        TreeState(nodes = List(15) { (1..50).random() }, message = "Initial Tree")
    )
    val state: StateFlow<TreeState> = _state

    private var speed = 1500L
    private var isPaused = false

    fun startTraversal() {
        viewModelScope.launch {
            useCase.inorder(
                _state.value.nodes,
                { speed },
                { isPaused }
            ) {
                _state.value = it
            }
        }
    }
}