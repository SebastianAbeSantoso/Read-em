package com.example.proyek_akhir_kewirausahaan.viewmodel

import com.example.proyek_akhir_kewirausahaan.domain.model.Book
import com.example.proyek_akhir_kewirausahaan.model.data.AvatarLook
import com.example.proyek_akhir_kewirausahaan.model.data.UserProfileData

data class ReademUiState(
    val books: List<Book> = emptyList(),
    val removedBookIds: Set<String> = emptySet(),
    val userProfile: UserProfileData? = null,
    val avatarLook: AvatarLook? = null,
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val fontSize: Float = 18f,
    val dailyReminders: Boolean = true,
    val newReleases: Boolean = false
)