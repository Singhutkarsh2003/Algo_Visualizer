package com.example.algovisualizer.presentation.visualizer.sorting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algovisualizer.domain.model.ArrayState
import com.example.algovisualizer.domain.usecase.dsa.sorting.BubbleSort
import com.example.algovisualizer.domain.usecase.dsa.sorting.InsertionSort
import com.example.algovisualizer.domain.usecase.dsa.sorting.MergeSort
import com.example.algovisualizer.domain.usecase.dsa.sorting.QuickSort
import com.example.algovisualizer.domain.usecase.dsa.sorting.SelectionSort
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SortingViewModel : ViewModel() {

    private val _arrayState = MutableStateFlow(
        ArrayState(number = List(20) { (10..100).random() }, null)
    )
    val arrayState: StateFlow<ArrayState> = _arrayState

    private val bubbleSort = BubbleSort()
    private val selectionSort = SelectionSort()
    private val insertionSort = InsertionSort()
    private val mergeSort = MergeSort()
    private val quickSort = QuickSort()

    private var isPaused = false
    private var speed = 50L

    // ONLY ONE SOURCE OF TRUTH
    enum class SortType {
        BUBBLE, SELECTION, INSERTION, MERGE, QUICK
    }

    private var selectedAlgorithm = SortType.BUBBLE

    // SET ALGORITHM
    fun setAlgorithm(type: SortType) {
        selectedAlgorithm = type
    }

    // GET NAME FOR UI
    fun getCurrentAlgorithm(): String {
        return when (selectedAlgorithm) {
            SortType.BUBBLE -> "Bubble Sort"
            SortType.SELECTION -> "Selection Sort"
            SortType.INSERTION -> "Insertion Sort"
            SortType.MERGE -> "Merge Sort"
            SortType.QUICK -> "Quick Sort"
        }
    }

    fun setSpeed(newSpeed: Float) {
        speed = newSpeed.toLong()
    }

    fun togglePause() {
        isPaused = !isPaused
    }

    // FIXED SORTING
    fun startSorting() {
        viewModelScope.launch {

            when (selectedAlgorithm) {

                SortType.BUBBLE -> bubbleSort.sort(
                    _arrayState.value.number,
                    { speed },
                    { isPaused }
                ) { _arrayState.value = it }

                SortType.SELECTION -> selectionSort.sort(
                    _arrayState.value.number,
                    { speed },
                    { isPaused }
                ) { _arrayState.value = it }

                SortType.INSERTION -> insertionSort.sort(
                    _arrayState.value.number,
                    { speed },
                    { isPaused }
                ) { _arrayState.value = it }

                SortType.MERGE -> mergeSort.sort(
                    _arrayState.value.number,
                    { speed },
                    { isPaused }
                ) { _arrayState.value = it }

                SortType.QUICK -> quickSort.sort(
                    _arrayState.value.number,
                    { speed },
                    { isPaused }
                ) { _arrayState.value = it }
            }
        }
    }

    fun generateNewArray() {
        _arrayState.value = ArrayState(List(20) { (10..100).random() })
    }
}