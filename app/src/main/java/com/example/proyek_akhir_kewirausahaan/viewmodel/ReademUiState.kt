package com.example.proyek_akhir_kewirausahaan.viewmodel

import com.example.proyek_akhir_kewirausahaan.ReademScreen
import com.example.proyek_akhir_kewirausahaan.model.data.BookData

data class ReademUiState(
    val currentScreen: ReademScreen = ReademScreen.Feed,
    val books: List<BookData> = emptyList(),
    val isLoading: Boolean = false,
    // Settings specific state
    val fontSize: Float = 18f,
    val dailyReminders: Boolean = true,
    val newReleases: Boolean = false
)