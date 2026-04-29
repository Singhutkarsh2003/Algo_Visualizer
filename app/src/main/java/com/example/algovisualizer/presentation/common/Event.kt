package com.example.algovisualizer.presentation.common

 sealed  class Event {
     data class  ShowToast(val message: String): Event()
     object  NavigateHome: Event()
}