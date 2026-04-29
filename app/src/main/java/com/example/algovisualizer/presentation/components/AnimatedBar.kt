package com.example.algovisualizer.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedBar(
    value: Int,
    isActive: Boolean
){

    val animatedHeight by animateDpAsState(
        targetValue = value.dp,
        label =""
    )
    Box(modifier = Modifier.width(12.dp).height(animatedHeight)
        .background(
              if(isActive) Color.Red else Color.Green
        )
    )

}