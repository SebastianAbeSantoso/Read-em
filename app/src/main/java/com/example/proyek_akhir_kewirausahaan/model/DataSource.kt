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
            coverResId = R.drawable.ic_launcher_background,
            genres = listOf("Metaphysics", "Noor Release"),
            synopsis = "Traveling from the brutalist libraries of tech cities to silent monasteries...",
            totalChapters = 12,
            readingTimeMin = 18,
            rating = 4.8,
            readersCount = 12400,
            isPremium = true,
            premiumUnlockXp = 500
        ),
        BookData(
            id = "book_002",
            title = "Whispers of the Silicon Forest",
            author = "Elena Vance",
            coverResId = R.drawable.ic_launcher_background,
            genres = listOf("Cyberpunk", "Mystery"),
            synopsis = "In a world where trees are made of fiber optics, a detective searches for a ghost in the machine.",
            totalChapters = 24,
            readingTimeMin = 45,
            rating = 4.5,
            readersCount = 8200,
            isPremium = false
        ),
        BookData(
            id = "book_003",
            title = "The Alchemist's Shadow",
            author = "Julian Thorne",
            coverResId = R.drawable.ic_launcher_background,
            genres = listOf("Fantasy", "Adventure"),
            synopsis = "A young apprentice discovers that his master's shadow has a life of its own.",
            totalChapters = 18,
            readingTimeMin = 32,
            rating = 4.9,
            readersCount = 15600,
            isPremium = true,
            premiumUnlockXp = 750
        ),
        BookData(
            id = "book_004",
            title = "Echoes of the Void",
            author = "Sarah Jenkins",
            coverResId = R.drawable.ic_launcher_background,
            genres = listOf("Sci-Fi", "Philosophy"),
            synopsis = "When humanity first reached the void, it didn't find nothingness. It found echoes of itself.",
            totalChapters = 15,
            readingTimeMin = 25,
            rating = 4.7,
            readersCount = 5400,
            isPremium = false
        ),
        BookData(
            id = "book_005",
            title = "The Midnight Library of Alexandria",
            author = "Marcus Aurelius (Modern)",
            coverResId = R.drawable.ic_launcher_background,
            genres = listOf("History", "Magic Realism"),
            synopsis = "What if the Great Library never burned, but was hidden in the folds of time?",
            totalChapters = 30,
            readingTimeMin = 60,
            rating = 4.6,
            readersCount = 21000,
            isPremium = true,
            premiumUnlockXp = 1000
        ),
        BookData(
            id = "book_006",
            title = "Beyond the Event Horizon",
            author = "Dr. Aris Thorne",
            coverResId = R.drawable.ic_launcher_background,
            genres = listOf("Space Opera", "Romance"),
            synopsis = "Two scientists on opposite sides of a black hole find a way to communicate through gravity waves.",
            totalChapters = 20,
            readingTimeMin = 40,
            rating = 4.3,
            readersCount = 3800,
            isPremium = false
        )
    )
    val chapters = listOf(
        ChapterData(
            id = "ch_001_01",
            bookId = "book_001",
            number = 1,
            title = "The Architecture of Quiet",
            content = "The last time Berlin didn't just feel distant, it felt thick..."
        ),
        ChapterData(
            id = "ch_002_01",
            bookId = "book_002",
            number = 1,
            title = "Neon Sap",
            content = "The rain in Neo-Tokyo tasted like ozone and burnt copper..."
        )
    )
    val userProfile = UserProfileData(
        id = "user_local",
        name = "Gojo Satoru",
        avatarRes = R.drawable.ic_launcher_foreground,
        secondaryAvatarRes = R.drawable.ic_launcher_foreground,
        rankTitle = "Premium Archivist",
        level = 42,
        xp = 12450,
        xpToNextLevel = 15000,
        streakDays = 48,
        totalBooksRead = 1204,
        totalNotes = 84000,
        credentials = listOf("POLYMATH", "DEEP THINKER", "SCRIBE", "CURATOR"),
        weeklyXp = listOf(200, 800, 500, 1000)
    )
    val readSessions = mutableListOf(
        ReadSessionData("book_001", currentChapter = 3, progressPercent = 0.27f,
            lastReadAt = System.currentTimeMillis(), status = ReadStatus.IN_PROGRESS),
        ReadSessionData("book_003", currentChapter = 18, progressPercent = 1f,
            lastReadAt = System.currentTimeMillis() - 86400000L, status = ReadStatus.COMPLETED),
    )
}
