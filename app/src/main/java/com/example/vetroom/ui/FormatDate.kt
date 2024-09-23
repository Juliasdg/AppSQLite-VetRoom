package com.example.vetroom.ui

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(input: String): String {
    val cleanString = input.replace("[^\\d]".toRegex(), "")
    val dateFormatter = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
    val outputFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return try {
        val parsedDate = dateFormatter.parse(cleanString)
        parsedDate?.let { outputFormatter.format(it) } ?: input
    } catch (e: Exception) {
        input
    }
}
