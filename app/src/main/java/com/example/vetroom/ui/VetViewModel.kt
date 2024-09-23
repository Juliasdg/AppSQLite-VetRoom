package com.example.vetroom.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vetroom.data.Vet
import com.example.vetroom.data.VetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class VetViewModel(private val repository: VetRepository) : ViewModel() {

    val vetList: Flow<List<Vet>> = repository.getAllVet()

    fun getVetById(id: Int): Flow<Vet> = repository.getVetById(id)

    fun addOrUpdateVet(id: Int? = null, name: String, race: String, age: Int, date: String, gender: String) {
        val vet = Vet(id = id ?: 0, name = name, race = race, age = age, date = date, gender = gender)
        viewModelScope.launch {
            repository.insertVet(vet)
        }
    }

    fun deleteVet(vet: Vet) {
        viewModelScope.launch {
            repository.deleteVet(vet)
        }
    }
}
