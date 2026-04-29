package com.example.algovisualizer.presentation.visualizer.step

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algovisualizer.domain.model.Step
import com.example.algovisualizer.domain.usecase.algorithm.GetStepsUseCase
import com.example.algovisualizer.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StepViewModel(
    private val getStepsUseCase: GetStepsUseCase
): ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Step>>>(UiState.Loading)
    val state : StateFlow<UiState<List<Step>>> = _state

    fun loadingSteps(algoId: Int){
        viewModelScope.launch {
            try {
                val steps = getStepsUseCase(algoId)
                _state.value = UiState.Success(data = steps)
            }catch (e: Exception){
                _state.value = UiState.Error(e.message ?: "Unknow error")
            }
        }
    }

}