package com.example.algovisualizer.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.algovisualizer.domain.usecase.algorithm.AlgoUseCases
import com.example.algovisualizer.presentation.visualizer.sorting.SortingViewModel
import com.example.algovisualizer.presentation.visualizer.step.StepViewModel

class ViewModelFactory(
    private val useCases: AlgoUseCases
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {


            // Sorting
            modelClass.isAssignableFrom(SortingViewModel::class.java) -> {
                SortingViewModel() as T
            }

            //  Steps
            modelClass.isAssignableFrom(StepViewModel::class.java) -> {
                StepViewModel(useCases.getSteps) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel")
        }
    }
}