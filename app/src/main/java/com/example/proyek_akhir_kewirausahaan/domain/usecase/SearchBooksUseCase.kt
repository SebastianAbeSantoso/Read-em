package com.example.proyek_akhir_kewirausahaan.domain.usecase

import com.example.proyek_akhir_kewirausahaan.domain.model.Book
import com.example.proyek_akhir_kewirausahaan.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class SearchBooksUseCase(private val repository: BookRepository) {
    operator fun invoke(query: String): Flow<List<Book>> {
        return repository.searchBooks(query)
    }
}
