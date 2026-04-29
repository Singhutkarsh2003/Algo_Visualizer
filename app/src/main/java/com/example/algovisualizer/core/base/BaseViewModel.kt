package com.example.algovisualizer.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class  baseViewModel : ViewModel() {

    protected fun launch(block : suspend () -> Unit) {
        viewModelScope.launch {
            block()
        }
    }
}