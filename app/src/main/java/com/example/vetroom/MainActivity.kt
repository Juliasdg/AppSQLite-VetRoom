package com.example.vetroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.vetroom.data.AppContainer
import com.example.vetroom.ui.navigation.VetNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = AppContainer(applicationContext)
        val pokemonRepository = appContainer.vetRepository

        setContent {
            val navController = rememberNavController()
            VetNavGraph(navController, pokemonRepository)
        }
    }
}
