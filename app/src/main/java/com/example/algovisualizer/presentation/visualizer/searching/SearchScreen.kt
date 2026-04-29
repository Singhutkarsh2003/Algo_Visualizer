package com.example.algovisualizer.presentation.visualizer.searching

import android.text.Layout
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

@Composable
fun SearchScreen(viewModel: SearchViewModel) {

    val state by viewModel.state.collectAsState()
    var target by remember { mutableStateOf("") }
    var sliderPosition by remember { mutableFloatStateOf(1500f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //  TITLE
        Text(
            text = "Searching Visualizer",
            modifier = Modifier.padding(top = 30.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        //  MESSAGE
        Text(
            text = state.message,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        //  GRAPH
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {

            state.number.forEachIndexed { index, value ->

                val height by animateDpAsState(
                    targetValue = (value * 2).dp,
                    label = ""
                )

                val color = when {
                    state.foundIndex == index -> Color.Red
                    state.activeIndices?.first == index -> Color.DarkGray
                    state.activeIndices?.second == index -> Color.Blue
                    else -> Color.Green
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.padding(2.dp)
                ) {

                    // NUMBER ABOVE BAR
                    Text(
                        text = value.toString(),
                        style = MaterialTheme.typography.labelSmall
                    )

                    Box(
                        modifier = Modifier
                            .width(14.dp)
                            .height(height)
                            .background(color)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //  INPUT FIELD
        BasicTextField(
            value = target,
            onValueChange = { target = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(10.dp),
            decorationBox = { innerTextField ->
                if (target.isEmpty()) {
                    Text("Enter number to search")
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        //  SEARCH BUTTONS
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            Button(onClick = {
                target.toIntOrNull()?.let {
                    viewModel.startLinearSearch(it)
                }
            }) {
                Text("Linear")
            }

            Button(onClick = {
                target.toIntOrNull()?.let {
                    viewModel.startBinarySearch(it)
                }
            }) {
                Text("Binary")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        //  CONTROL BUTTONS
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            Button(onClick = {
                target.toIntOrNull()?.let {
                    viewModel.startLinearSearch(it) // default start
                }
            }) {
                Text("Start")
            }

            Button(onClick = { viewModel.togglePause() }) {
                Text("Pause / Resume")
            }

            Button(onClick = { viewModel.generateNewArray() }) {
                Text("Reset")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // SPEED CONTROL
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