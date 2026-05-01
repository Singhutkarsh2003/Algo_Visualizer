package com.example.algovisualizer.presentation.visualizer.tree

import android.R.attr.textSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.example.algovisualizer.domain.model.TreeNode

@Composable
fun TreeScreen(viewModel: TreeViewModel) {

    val state by viewModel.state.collectAsState()
    var sliderPosition by remember { mutableFloatStateOf(1500f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // TITLE
        Text(
            text = "Tree Visualizer",
            modifier = Modifier.padding(top = 50.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // MESSAGE
        Text(text = state.message)

        Spacer(modifier = Modifier.height(16.dp))

        //  TREE DRAWING
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {

            fun drawNode(
                node: TreeNode?,
                x: Float,
                y: Float,
                offset: Float
            ) {
                if (node == null) return

                val isVisited = state.visited.contains(node.value)

                var color = if (isVisited) Color.Green else Color.LightGray

                // Draw circle
                drawCircle(
                    color = color,
                    radius = 30f,
                    center = androidx.compose.ui.geometry.Offset(x, y)
                )

                // Draw value
                val textPaint = android.graphics.Paint().apply {
                    textSize = 32f
                    color = Color.Black
                    textAlign = android.graphics.Paint.Align.CENTER
                    isAntiAlias = true
                }

                drawContext.canvas.nativeCanvas.drawText(
                    node.value.toString(),
                    x,
                    y + 10f,
                    textPaint
                )

                // LEFT
                node.left?.let {
                    drawLine(
                        color = Color.Black,
                        start = androidx.compose.ui.geometry.Offset(x, y),
                        end = androidx.compose.ui.geometry.Offset(x - offset, y + 120),
                        strokeWidth = 4f
                    )
                    drawNode(it, x - offset, y + 120, offset / 2)
                }

                // RIGHT
                node.right?.let {
                    drawLine(
                        color = Color.Black,
                        start = androidx.compose.ui.geometry.Offset(x, y),
                        end = androidx.compose.ui.geometry.Offset(x + offset, y + 120),
                        strokeWidth = 4f
                    )
                    drawNode(it, x + offset, y + 120, offset / 2)
                }
            }

            drawNode(
                node = state.root,
                x = size.width / 2,
                y = 80f,
                offset = size.width / 4
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ALGORITHM BUTTONS
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(onClick = { viewModel.startDFS() }) {
                Text("DFS")
            }
            Button(onClick = { viewModel.startBFS() }) {
                Text("BFS")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //  CONTROL BUTTONS
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            Button(onClick = { viewModel.startDFS() }) {
                Text("Start")
            }

            Button(onClick = { viewModel.togglePause() }) {
                Text("Pause / Resume")
            }

            Button(onClick = { viewModel.reset() }) {
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
            valueRange = 100f..1500f
        )
    }
}