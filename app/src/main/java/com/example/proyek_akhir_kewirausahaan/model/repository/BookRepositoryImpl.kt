package com.example.proyek_akhir_kewirausahaan.model.repository

import com.example.proyek_akhir_kewirausahaan.model.DataSource
import com.example.proyek_akhir_kewirausahaan.model.data.BookData
import com.example.proyek_akhir_kewirausahaan.model.data.ChapterData
import com.example.proyek_akhir_kewirausahaan.model.data.ReadSessionData
import com.example.proyek_akhir_kewirausahaan.model.data.UserProfileData

class BookRepositoryImpl : BookRepository {

    override fun getAllBooks(): List<BookData> {
        return DataSource.books
    }

    override fun getBook(id: String): BookData {
        return DataSource.books.first { it.id == id }
    }

    override fun getChapters(bookId: String): List<ChapterData> {
        return DataSource.chapters.filter { it.bookId == bookId }
    }

    override fun getSession(bookId: String): ReadSessionData? {
        return DataSource.readSessions.firstOrNull { it.bookId == bookId }
    }

    override fun getUserProfile(): UserProfileData {
        return DataSource.userProfile
    }

    override fun updateSession(session: ReadSessionData) {
        val index = DataSource.readSessions.indexOfFirst {
            it.bookId == session.bookId
        }

        if (index != -1) {
            DataSource.readSessions[index] = session
        } else {
            DataSource.readSessions.add(session)
        }
    }
}