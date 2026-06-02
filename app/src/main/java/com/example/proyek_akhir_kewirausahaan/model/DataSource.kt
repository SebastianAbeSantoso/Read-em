package com.example.proyek_akhir_kewirausahaan.model

import com.example.proyek_akhir_kewirausahaan.R
import com.example.proyek_akhir_kewirausahaan.model.data.BookData
import com.example.proyek_akhir_kewirausahaan.model.data.ChapterData
import com.example.proyek_akhir_kewirausahaan.model.data.ReadSessionData
import com.example.proyek_akhir_kewirausahaan.model.data.ReadStatus
import com.example.proyek_akhir_kewirausahaan.model.data.UserProfileData

object DataSource {
    val books = listOf(
        BookData(
            id = "book_001",
            title = "The Kinetic Theory of Silence",
            author = "Diana V. Sterling",
            coverResId = R.drawable.ic_launcher_background, // TODO: Replace Placeholder
            genres = listOf("Metaphysics", "Noor Release"),
            synopsis = "Traveling from the brutalist libraries of tech cities to silent monasteries...",
            totalChapters = 12,
            readingTimeMin = 18,
            rating = 4.8,
            readersCount = 12400,
            isPremium = true,
            premiumUnlockXp = 500
        )
    )
    val chapters = listOf(
        ChapterData(
            id = "ch_001_01",
            bookId = "book_001",
            number = 1,
            title = "The Architecture of Quiet",
            content = "The last time Berlin didn't just feel distant, it felt thick..." // long string
        )
    )
    val userProfile = UserProfileData(
        id = "user_local",
        name = "Alex Vandervolt",
        avatarRes = R.drawable.ic_launcher_foreground, // TODO: replace placeholder
        level = 42,
        xp = 12450,
        xpToNextLevel = 15000,
        streakDays = 48,
        totalBooksRead = 31,
        totalPagesRead = 4218
    )
    val readSessions = mutableListOf(
        ReadSessionData("book_001", currentChapter = 3, progressPercent = 0.27f,
            lastReadAt = System.currentTimeMillis(), status = ReadStatus.IN_PROGRESS),
        ReadSessionData("book_002", currentChapter = 18, progressPercent = 1f,
            lastReadAt = System.currentTimeMillis() - 86400000L, status = ReadStatus.COMPLETED),
    )
}