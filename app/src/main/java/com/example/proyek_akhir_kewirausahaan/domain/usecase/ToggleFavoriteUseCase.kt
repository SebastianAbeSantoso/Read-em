package com.example.proyek_akhir_kewirausahaan.domain.usecase

import com.example.proyek_akhir_kewirausahaan.domain.repository.BookRepository

class ToggleFavoriteUseCase(private val repository: BookRepository) {
    suspend operator fun invoke(bookId: String) {
        repository.toggleFavorite(bookId)
    }
}
