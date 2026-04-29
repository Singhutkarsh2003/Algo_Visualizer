package com.example.algovisualizer.presentation.visualizer.sorting

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

fun SortingScreen(viewModel: SortingViewModel = viewModel()) {

    val state by viewModel.arrayState.collectAsState()

    // FIXED WARNING
    var sliderPosition by remember { mutableFloatStateOf(1500f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // important for small screens
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //  TITLE
        Text(
            text = "Sorting Visualizer",
            modifier = Modifier.padding(top = 50.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        //  CURRENT ALGO
        Text(
            text = "Algorithm: ${viewModel.getCurrentAlgorithm()}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // GRAPH AREA
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {

            state.number.forEachIndexed { index, value ->

                val animatedHeight by animateDpAsState(
                    targetValue = (value * 2).dp,
                    label = ""
                )

                val color = when {
                    state.activeIndices?.first == index ||
                            state.activeIndices?.second == index -> Color.Red
                    else -> Color.Green
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.padding(2.dp)
                ) {

                    // NUMBER
                    Text(
                        text = value.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Black
                    )

                    // BAR
                    Box(
                        modifier = Modifier
                            .width(12.dp)
                            .height(animatedHeight)
                            .background(color)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //  ALGORITHM BUTTONS
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { viewModel.setAlgorithm(SortingViewModel.SortType.BUBBLE) }) {
                    Text("Bubble")
                }
                Button(onClick = { viewModel.setAlgorithm(SortingViewModel.SortType.SELECTION) }) {
                    Text("Selection")
                }
                Button(onClick = { viewModel.setAlgorithm(SortingViewModel.SortType.INSERTION) }) {
                    Text("Insertion")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { viewModel.setAlgorithm(SortingViewModel.SortType.MERGE) }) {
                    Text("Merge")
                }
                Button(onClick = { viewModel.setAlgorithm(SortingViewModel.SortType.QUICK) }) {
                    Text("Quick")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        //  CONTROL BUTTONS
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            Button(onClick = { viewModel.startSorting() }) {
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

        //  SPEED CONTROL
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