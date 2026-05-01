package com.example.algovisualizer.presentation.visualizer.graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GraphScreen(viewModel: GraphViewModel) {

    val visited by viewModel.visited.collectAsState()
    val path by viewModel.path.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Canvas(modifier = Modifier.fillMaxWidth().height(400.dp)) {

            // Draw edges
            viewModel.graph.forEach { (from, edges) ->
                val fromNode = viewModel.nodes.first { it.id == from }

                edges.forEach { edge ->
                    val toNode = viewModel.nodes.first { it.id == edge.to }

                    drawLine(
                        color = Color.Gray,
                        start = Offset(fromNode.x, fromNode.y),
                        end = Offset(toNode.x, toNode.y),
                        strokeWidth = 4f
                    )
                }
            }

            // Draw nodes
            viewModel.nodes.forEach { node ->

                val color = when {
                    path.contains(node.id) -> Color.Red
                    visited.contains(node.id) -> Color.Green
                    else -> Color.LightGray
                }

                drawCircle(
                    color = color,
                    radius = 30f,
                    center = Offset(node.x, node.y)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Button(onClick = { viewModel.start() }) {
                Text("Start")
            }

            Button(onClick = { viewModel.togglePause() }) {
                Text("Pause")
            }
        }
    }
}