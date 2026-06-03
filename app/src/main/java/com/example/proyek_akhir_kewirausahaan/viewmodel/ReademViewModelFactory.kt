package com.example.proyek_akhir_kewirausahaan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyek_akhir_kewirausahaan.model.repository.BookRepository

class ReademViewModelFactory (
    private val bookRepository: BookRepository,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReademViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReademViewModel(bookRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}