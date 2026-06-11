package com.example.proyek_akhir_kewirausahaan.viewmodel

import com.example.proyek_akhir_kewirausahaan.domain.model.Book
import com.example.proyek_akhir_kewirausahaan.model.data.UserProfileData

data class ReademUiState(
    val books: List<Book> = emptyList(),
    val userProfile: UserProfileData? = null,
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    // Settings specific state
    val fontSize: Float = 18f,
    val dailyReminders: Boolean = true,
    val newReleases: Boolean = false
)
