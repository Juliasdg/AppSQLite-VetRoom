package com.example.pokemon.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vetroom.data.VetRepository
import com.example.vetroom.ui.VetViewModel

// A Factory para criar PokemonViewModel com o PokemonRepository
class VetViewModelFactory(private val repository: VetRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Verifica se a classe passada é a classe do ViewModel
        if (modelClass.isAssignableFrom(VetViewModel::class.java)) {
            return VetViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
