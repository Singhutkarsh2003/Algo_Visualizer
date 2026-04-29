package com.example.algovisualizer.presentation.visualizer.linkedlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algovisualizer.domain.model.LinkedListState
import com.example.algovisualizer.domain.usecase.dsa.linkedlsit.LinkedList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LinkedListViewModel : ViewModel() {

    private val useCase = LinkedList()

    private val _state = MutableStateFlow(
        LinkedListState(List(12) {(10..100).random()},
        message = "Inital List"
    )
    )
    val state: StateFlow<LinkedListState> = _state

    private var speed = 1500L
    private  var isPaused = false

    fun setSpeed(newSpeed: Float){
        speed = newSpeed.toLong()
    }
    fun togglePaused(){
       isPaused = !isPaused
    }

    fun insert(value: Int) {
        viewModelScope.launch {
            useCase.insert(
                list = _state.value.numbers.toMutableList(),
                value = value,
                delayTime = { speed },
                isPaused = { isPaused }
            ) { newState: LinkedListState ->
                _state.value = newState
            }
        }
    }

    fun delete(value : Int){
        viewModelScope.launch {
            useCase.delete(
                list = _state.value.numbers.toMutableList(),
                value = value,
                delayTime = {speed},
                isPaused = {isPaused}
            ){newState : LinkedListState ->
                _state.value = newState
            }
        }
    }

    fun search(value: Int){
        viewModelScope.launch {
            useCase.search(
                list = _state.value.numbers.toMutableList(),
                value = value,
                delayTime = {speed},
                isPaused = {isPaused}
            ){newState : LinkedListState ->
                _state.value = newState
            }
        }
    }

    fun generateNewArray() {
        _state.value = LinkedListState(List(12) { (10..100).random() })
    }


}