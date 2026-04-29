package com.example.algovisualizer.presentation.visualizer.linkedlist

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LinkedListScreen(viewModel: LinkedListViewModel = viewModel() ){

    val state by viewModel.state.collectAsState()
    var input by remember { mutableStateOf("") }
    var target by remember { mutableStateOf("") }

    var sliderPosition by remember { mutableFloatStateOf(1500f) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //  TITLE
        Text(
            text = "LinkedList Visualizer",
            modifier = Modifier.padding(top = 50.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        //  CURRENT ALGO
        Text(
            text = state.message,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value= input,
            onValueChange = {input = it},
            modifier = Modifier.fillMaxWidth().background(Color.LightGray).padding(8.dp),
            decorationBox = { innerTextField ->
                if (target.isEmpty()) {
                    Text("Enter number to search")
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            state.numbers.forEachIndexed { index, value ->

                val height by animateDpAsState(
                    targetValue = (value * 2).dp,
                    label = ""
                )
                val color = when{
                    state.activeIndex == index -> Color.Red
                    else -> Color.Green
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Box(
                        modifier = Modifier.width(20.dp).height(height).background(color)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = value.toString())
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    input.toIntOrNull()?.let {
                        viewModel.insert(it)
                    }
                }) {

                Text("Insert")
            }

            Button(onClick = {
                input.toIntOrNull()?.let {
                    viewModel.delete(it)
                }
            }) {
                Text("Delete")
            }

            Button(onClick = {
                input.toIntOrNull()?.let {
                    viewModel.search(it)
                }
            }) {
                Text("Search")
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        //  CONTROL BUTTONS
        Row(horizontalArrangement = Arrangement.Center) {

            Button(onClick = { viewModel.generateNewArray() }) {
                Text("Reset")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Speed")

        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
                viewModel.setSpeed(it)
            },
            valueRange = 50f..1500f
        )

    }
}