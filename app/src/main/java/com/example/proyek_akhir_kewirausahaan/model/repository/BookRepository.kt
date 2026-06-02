package com.example.proyek_akhir_kewirausahaan.model.repository

import com.example.proyek_akhir_kewirausahaan.model.data.BookData
import com.example.proyek_akhir_kewirausahaan.model.data.ChapterData
import com.example.proyek_akhir_kewirausahaan.model.data.ReadSessionData
import com.example.proyek_akhir_kewirausahaan.model.data.UserProfileData

interface BookRepository {
    fun getAllBooks(): List<BookData>
    fun getBook(id: String): BookData
    fun getChapters(bookId: String): List<ChapterData>
    fun getSession(bookId: String): ReadSessionData?
    fun getUserProfile(): UserProfileData
    fun updateSession(session: ReadSessionData)
}