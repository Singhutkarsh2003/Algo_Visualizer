package com.example.algovisualizer.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.algovisualizer.presentation.navigation.Routes

@Composable
fun HomeScreen(navController : NavHostController){

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = 40.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sorting Visualizer",
            modifier = Modifier.padding(top = 50.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Button(onClick = {navController.navigate((Routes.SORTING)) }) {
            Text("Sorting Visualizer")
        }

        Button(onClick = {navController.navigate(Routes.SEARCHING)}) {
            Text("Searching Visualizer")
        }

        Button(onClick = {navController.navigate(Routes.LINKED_LIST)}) {
            Text("Linked List Visualizer")
        }

        Button(onClick = {navController.navigate(Routes.TREE)}) {
            Text("Tree Visualizer")
        }

        Button(onClick = {navController.navigate(Routes.GRAPH)}) {
            Text("Graph Visualizer")
        }

        Button(onClick = {navController.navigate(Routes.GREEDY)}) {
            Text("Greedy Visualizer")
        }

    }

}