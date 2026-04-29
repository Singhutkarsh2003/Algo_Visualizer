package com.example.algovisualizer.presentation.visualizer.searching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algovisualizer.domain.model.SearchState
import com.example.algovisualizer.domain.usecase.dsa.searching.BinarySearch
import com.example.algovisualizer.domain.usecase.dsa.searching.LinearSearch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val linearSearch = LinearSearch()
    private val binarySearch = BinarySearch()

    private val _state = MutableStateFlow(
        SearchState(
            number = List(16) { (10..100).random() },
            message = "Initial List"
        )
    )
    val state: StateFlow<SearchState> = _state

    private var speed = 1500L
    private var isPaused = false

    fun setSpeed(newSpeed: Float) {
        speed = newSpeed.toLong()
    }

    fun togglePause() {
        isPaused = !isPaused
    }

    fun startLinearSearch(target: Int) {
        viewModelScope.launch {
            linearSearch.search(
                _state.value.number,
                target,
                { speed },
                { isPaused }
            ) {
                _state.value = it
            }
        }
    }

    fun startBinarySearch(target: Int) {
        viewModelScope.launch {
            binarySearch.search(
                _state.value.number,
                target,
                { speed },
                { isPaused }
            ) {
                _state.value = it
            }
        }
    }

    fun generateNewArray() {
        _state.value = SearchState(List(15) { (10..100).random() })
    }

}