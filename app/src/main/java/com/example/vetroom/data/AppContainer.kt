package com.example.vetroom.data

import android.content.Context
import androidx.room.Room

class AppContainer(context: Context) {
    private val database: VetDatabase by lazy {
        Room.databaseBuilder(context, VetDatabase::class.java, "vet_db").build()
    }

    val vetRepository: VetRepository by lazy {
        VetRepository(database.vetDao())
    }
}


