package com.example.vetroom.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Vet::class], version = 1, exportSchema = false)
abstract class VetDatabase : RoomDatabase() {
    abstract fun vetDao(): VetDao
}
