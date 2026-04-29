package com.example.algovisualizer.presentation.visualizer.greedy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algovisualizer.domain.model.Activity
import com.example.algovisualizer.domain.model.GreedyState
import com.example.algovisualizer.domain.usecase.greedy.ActivitySelection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class GreedyViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        GreedyState(activities = generateActivities())
    )
    val state: StateFlow<GreedyState> = _state

    private val activitySelection = ActivitySelection()

    private var isPaused = false
    private var speed = 1500L

    fun start() {
        viewModelScope.launch {
            activitySelection.selectActivities(
                activities = _state.value.activities,
                delayTime = { speed },
                isPaused = { isPaused }
            ) { newState ->
                _state.value = newState
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
        _state.value = GreedyState(
            activities = generateActivities(),
            message = "Reset done"
        )
    }

    private fun generateActivities(): List<Activity> {
        return List(8) {
            val start = (1..10).random()
            val end = start + (1..5).random()
            Activity(start, end)
        }
    }
}
