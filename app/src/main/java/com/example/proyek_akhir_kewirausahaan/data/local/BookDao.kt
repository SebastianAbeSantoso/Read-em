package com.example.proyek_akhir_kewirausahaan.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookById(id: String): Flow<BookEntity?>

    @Query("SELECT * FROM books WHERE title LIKE '%' || :query || '%' OR author LIKE '%' || :query || '%'")
    fun searchBooks(query: String): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBooks(books: List<BookEntity>)

    @Query("UPDATE books SET isFavorite = NOT isFavorite WHERE id = :bookId")
    suspend fun toggleFavorite(bookId: String)

    @Query("SELECT COUNT(*) FROM books")
    suspend fun getBooksCount(): Int
}
