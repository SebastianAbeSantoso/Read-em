package com.example.proyek_akhir_kewirausahaan.domain.repository

import com.example.proyek_akhir_kewirausahaan.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getAllBooks(): Flow<List<Book>>
    fun getBookById(id: String): Flow<Book?>
    fun searchBooks(query: String): Flow<List<Book>>
    suspend fun toggleFavorite(bookId: String)
}
