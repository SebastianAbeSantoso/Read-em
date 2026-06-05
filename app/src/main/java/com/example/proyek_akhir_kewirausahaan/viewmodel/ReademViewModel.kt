package com.example.proyek_akhir_kewirausahaan.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyek_akhir_kewirausahaan.model.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ReademViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        ReademUiState(
            books = bookRepository.getAllBooks()
        )
    )

    val uiState = _uiState.asStateFlow()

    fun updateFontSize(size: Float) {
        _uiState.update { it.copy(fontSize = size) }
    }

    fun toggleDailyReminders(enabled: Boolean) {
        _uiState.update { it.copy(dailyReminders = enabled) }
    }

    fun toggleNewReleases(enabled: Boolean) {
        _uiState.update { it.copy(newReleases = enabled) }
    }
}