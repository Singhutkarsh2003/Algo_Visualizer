package com.example.algovisualizer.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.algovisualizer.presentation.auth.AuthViewModel
import com.example.algovisualizer.presentation.auth.LoginScreen
import com.example.algovisualizer.presentation.auth.RegisterScreen
import com.example.algovisualizer.presentation.home.HomeScreen
import com.example.algovisualizer.presentation.visualizer.graph.GraphScreen
import com.example.algovisualizer.presentation.visualizer.graph.GraphViewModel
import com.example.algovisualizer.presentation.visualizer.greedy.GreedyScreen
import com.example.algovisualizer.presentation.visualizer.greedy.GreedyViewModel
import com.example.algovisualizer.presentation.visualizer.linkedlist.LinkedListScreen
import com.example.algovisualizer.presentation.visualizer.linkedlist.LinkedListViewModel
import com.example.algovisualizer.presentation.visualizer.searching.SearchScreen
import com.example.algovisualizer.presentation.visualizer.searching.SearchViewModel
import com.example.algovisualizer.presentation.visualizer.sorting.SortingScreen
import com.example.algovisualizer.presentation.visualizer.sorting.SortingViewModel
import com.example.algovisualizer.presentation.visualizer.tree.TreeScreen
import com.example.algovisualizer.presentation.visualizer.tree.TreeViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {

        //  LOGIN
        composable(Routes.LOGIN) {
            LoginScreen(
                viewModel = authViewModel,
                onSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onGoToRegister = {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }

        // REGISTER
        composable(Routes.HOME) {
            RegisterScreen(
                viewModel = authViewModel,
                onSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                },
                onGoTOLogin = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        // HOME
        composable(Routes.HOME) {
            HomeScreen(navController)
        }

        // All DSA Screens
        composable(Routes.SORTING){
            val viewModel: SortingViewModel = viewModel()
            SortingScreen(viewModel)
        }

        composable(Routes.SEARCHING){
            val viewModel: SearchViewModel = viewModel()
            SearchScreen(viewModel = viewModel)
        }

        composable(Routes.LINKED_LIST){
            val viewModel: LinkedListViewModel = viewModel()
            LinkedListScreen(viewModel = viewModel)
        }

        composable(Routes.TREE){
            val viewModel: TreeViewModel = viewModel()
            TreeScreen(viewModel = viewModel)
        }

        composable(Routes.GRAPH){
            val viewModel: GraphViewModel = viewModel()
            GraphScreen(viewModel = viewModel)
        }

        composable(Routes.GREEDY){
            val viewModel: GreedyViewModel = viewModel()
            GreedyScreen(viewModel = viewModel)
        }


    }
}