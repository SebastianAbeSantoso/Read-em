package com.example.proyek_akhir_kewirausahaan.model.data

data class UserProfileData(
    val id: String,
    val name: String,
    val avatarRes: Int,
    val level: Int,
    val xp: Int,
    val xpToNextLevel: Int,
    val streakDays: Int,
    val totalBooksRead: Int,
    val totalPagesRead: Int
)
