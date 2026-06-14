package com.example.proyek_akhir_kewirausahaan.domain.usecase

import com.example.proyek_akhir_kewirausahaan.model.data.UserProfileData
import com.example.proyek_akhir_kewirausahaan.model.repository.BookRepository

class GetUserProfileUseCase(private val repository: BookRepository) {
    operator fun invoke(): UserProfileData {
        return repository.getUserProfile()
    }
}