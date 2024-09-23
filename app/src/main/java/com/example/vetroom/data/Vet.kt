package com.example.vetroom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vet")
data class Vet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val race: String,
    val age: Int,
    val date: String,
    val gender: String
)
