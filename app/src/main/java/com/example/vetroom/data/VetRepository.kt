package com.example.vetroom.data

import kotlinx.coroutines.flow.Flow

open class VetRepository(private val vetDao: VetDao) {
    fun getAllVet(): Flow<List<Vet>> = vetDao.getAllVet()

    fun getVetById(id: Int): Flow<Vet> = vetDao.getVetById(id)

    suspend fun insertVet(vet: Vet) = vetDao.insertVet(vet)

    suspend fun deleteVet(vet: Vet) = vetDao.deleteVet(vet)
}
