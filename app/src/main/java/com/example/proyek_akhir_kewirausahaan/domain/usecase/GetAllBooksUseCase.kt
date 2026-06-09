package com.example.proyek_akhir_kewirausahaan.domain.usecase

import com.example.proyek_akhir_kewirausahaan.domain.model.Book
import com.example.proyek_akhir_kewirausahaan.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetAllBooksUseCase(private val repository: BookRepository) {
    operator fun invoke(): Flow<List<Book>> {
        return repository.getAllBooks()
    }
}
