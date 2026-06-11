package com.example.proyek_akhir_kewirausahaan.model.data

data class UserProfileData(
    val id: String,
    val name: String,
    val avatarRes: Int,
    val secondaryAvatarRes: Int? = null,
    val rankTitle: String,
    val level: Int,
    val xp: Int,
    val xpToNextLevel: Int,
    val streakDays: Int,
    val totalBooksRead: Int,
    val totalNotes: Int,
    val credentials: List<String> = emptyList(),
    val weeklyXp: List<Int> = emptyList() // XP for last 4 weeks
)
