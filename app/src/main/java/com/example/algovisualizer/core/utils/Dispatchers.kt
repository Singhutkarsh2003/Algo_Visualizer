package com.example.algovisualizer.core.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatchersProvider{
    val io : CoroutineDispatcher = Dispatchers.IO
    val main : CoroutineDispatcher = Dispatchers.Main
}