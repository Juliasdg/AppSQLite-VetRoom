package com.example.vetroom.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VetDao {
    @Query("SELECT * FROM vet")
    fun getAllVet(): Flow<List<Vet>>

    @Query("SELECT * FROM vet WHERE id = :id")
    fun getVetById(id: Int): Flow<Vet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVet(vet: Vet)

    @Delete
    suspend fun deleteVet(vet: Vet)
}
