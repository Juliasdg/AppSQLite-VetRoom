package com.example.vetroom.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pokemon.ui.VetViewModelFactory
import com.example.vetroom.ui.HomeScreen
import com.example.vetroom.data.VetRepository
import com.example.vetroom.ui.VetViewModel
import com.example.vetroom.ui.VetScreen

@Composable
fun VetNavGraph(navController: NavHostController, vetRepository: VetRepository) {
    val viewModel: VetViewModel = viewModel(factory = VetViewModelFactory(vetRepository))

    NavHost(navController, startDestination = "homeScreen") {
        composable("homeScreen") { HomeScreen(navController) }
        composable("vetScreen") { VetScreen(viewModel) }
    }
}
