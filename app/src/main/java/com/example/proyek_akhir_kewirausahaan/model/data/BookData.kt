package com.example.proyek_akhir_kewirausahaan.model.data

import androidx.annotation.DrawableRes

data class BookData(
    val id: String,
    val title: String,
    val author: String,
    @DrawableRes val coverResId: Int,
    val genres: List<String>,
    val synopsis: String,
    val totalChapters: Int,
    val readingTimeMin: Int,
    val rating: Double,
    val readersCount: Int,
    val isPremium: Boolean = false,
    val premiumUnlockXp: Int = 0
)
