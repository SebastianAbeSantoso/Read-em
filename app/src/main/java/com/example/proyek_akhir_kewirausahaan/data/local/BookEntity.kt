package com.example.proyek_akhir_kewirausahaan.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyek_akhir_kewirausahaan.domain.model.Book

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val coverResId: Int,
    val synopsis: String,
    val totalChapters: Int,
    val readingTimeMin: Int,
    val rating: Double,
    val readersCount: Int,
    val isPremium: Boolean,
    val isFavorite: Boolean = false
) {
    fun toDomainModel(): Book {
        return Book(
            id = id,
            title = title,
            author = author,
            coverResId = coverResId,
            genres = emptyList(), // Room doesn't support List easily, we can add TypeConverters later if needed
            synopsis = synopsis,
            totalChapters = totalChapters,
            readingTimeMin = readingTimeMin,
            rating = rating,
            readersCount = readersCount,
            isPremium = isPremium,
            isFavorite = isFavorite
        )
    }

    companion object {
        fun fromDomainModel(book: Book): BookEntity {
            return BookEntity(
                id = book.id,
                title = book.title,
                author = book.author,
                coverResId = book.coverResId,
                synopsis = book.synopsis,
                totalChapters = book.totalChapters,
                readingTimeMin = book.readingTimeMin,
                rating = book.rating,
                readersCount = book.readersCount,
                isPremium = book.isPremium,
                isFavorite = book.isFavorite
            )
        }
    }
}
