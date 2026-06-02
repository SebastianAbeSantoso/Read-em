package com.example.proyek_akhir_kewirausahaan.model.data

data class ReadSessionData(
    val bookId: String,
    val currentChapter: Int,
    val progressPercent: Float,  // 0f..1f
    val lastReadAt: Long,        // epoch ms
    val status: ReadStatus
)
