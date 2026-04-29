package com.example.algovisualizer.presentation.visualizer.tree

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TreeScreen(viewModel: TreeViewModel) {

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Tree Visualizer",
            modifier = Modifier.padding(top = 50.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        //  MESSAGE
        Text(
            text = state.message,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // SIMPLE TREE VISUAL (Row grid)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            state.nodes.chunked(4).forEach { row ->
                Row(horizontalArrangement = Arrangement.Center) {
                    row.forEach { value ->

                        val color = when {
                            state.current == value -> Color.Red
                            state.visited.contains(value) -> Color.Green
                            else -> Color.Gray
                        }

                        Box(
                            modifier = Modifier
                                .padding(6.dp)
                                .size(40.dp)
                                .background(color),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(value.toString(), color = Color.White)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { viewModel.startTraversal() }) {
            Text("Start Inorder")
        }
    }
}