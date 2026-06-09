package com.example.proyek_akhir_kewirausahaan.data.repository

import com.example.proyek_akhir_kewirausahaan.data.local.BookDao
import com.example.proyek_akhir_kewirausahaan.data.local.BookEntity
import com.example.proyek_akhir_kewirausahaan.domain.model.Book
import com.example.proyek_akhir_kewirausahaan.domain.repository.BookRepository
import com.example.proyek_akhir_kewirausahaan.model.DataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(private val bookDao: BookDao) : BookRepository {

    override fun getAllBooks(): Flow<List<Book>> {
        return bookDao.getAllBooks().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun getBookById(id: String): Flow<Book?> {
        return bookDao.getBookById(id).map { it?.toDomainModel() }
    }

    override fun searchBooks(query: String): Flow<List<Book>> {
        return bookDao.searchBooks(query).map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun toggleFavorite(bookId: String) {
        bookDao.toggleFavorite(bookId)
    }

    suspend fun initializeDatabaseIfNeeded() {
        if (bookDao.getBooksCount() == 0) {
            val initialBooks = DataSource.books.map { bookData ->
                BookEntity(
                    id = bookData.id,
                    title = bookData.title,
                    author = bookData.author,
                    coverResId = bookData.coverResId,
                    synopsis = bookData.synopsis,
                    totalChapters = bookData.totalChapters,
                    readingTimeMin = bookData.readingTimeMin,
                    rating = bookData.rating,
                    readersCount = bookData.readersCount,
                    isPremium = bookData.isPremium,
                    isFavorite = false
                )
            }
            bookDao.insertBooks(initialBooks)
        }
    }
}
