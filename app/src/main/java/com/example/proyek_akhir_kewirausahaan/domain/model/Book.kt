package com.example.proyek_akhir_kewirausahaan.domain.model

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val coverResId: Int,
    val genres: List<String>,
    val synopsis: String,
    val totalChapters: Int,
    val readingTimeMin: Int,
    val rating: Double,
    val readersCount: Int,
    val isPremium: Boolean = false,
    val isFavorite: Boolean = false
)
