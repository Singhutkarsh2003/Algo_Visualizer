package com.example.algovisualizer.presentation.visualizer.greedy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GreedyScreen(viewModel: GreedyViewModel) {

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Greedy Visualizer",
            modifier = Modifier.padding(top = 50.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        //  MESSAGE
        Text(
            text = state.message,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        state.activities.forEach {
            val selected = state.selected.contains(it)

            Text(
                "${it.start}-${it.end}",
                color = if (selected) Color.Green else Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Button(onClick = { viewModel.start() }) { Text("Start") }
            Button(onClick = { viewModel.togglePause() }) { Text("Pause") }
            Button(onClick = { viewModel.reset() }) { Text("Reset") }
        }
    }
}