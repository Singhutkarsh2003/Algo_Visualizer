package com.example.algovisualizer.presentation.visualizer.tree

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algovisualizer.domain.model.TreeNode
import com.example.algovisualizer.domain.model.TreeState
import com.example.algovisualizer.domain.usecase.dsa.tree.TreeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TreeViewModel : ViewModel() {

    private val useCase = TreeUseCase()

    private val _state = MutableStateFlow(
        TreeState(root = createTree())
    )
    val state: StateFlow<TreeState> = _state

    private var isPaused = false
    private var speed = 500L

    fun startDFS() {
        viewModelScope.launch {
            useCase.dfs(
                root = _state.value.root,
                delayTime = { speed },
                isPaused = { isPaused }
            ) {
                _state.value = it
            }
        }
    }

    fun startBFS() {
        viewModelScope.launch {
            useCase.bfs(
                root = _state.value.root,
                delayTime = { speed },
                isPaused = { isPaused }
            ) {
                _state.value = it
            }
        }
    }

    fun togglePause() {
        isPaused = !isPaused
    }

    fun setSpeed(value: Float) {
        speed = value.toLong()
    }

    fun reset() {
        _state.value = TreeState(root = createTree(), message = "Reset done")
    }

    private fun createTree(): TreeNode {
        return TreeNode(
            10,
            TreeNode(5,
                TreeNode(2),
                TreeNode(7)
            ),
            TreeNode(15,
                TreeNode(12),
                TreeNode(20)
            )
        )
    }
}