package com.example.proyek_akhir_kewirausahaan.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyek_akhir_kewirausahaan.data.local.AppDatabase
import com.example.proyek_akhir_kewirausahaan.data.local.UserPreferences
import com.example.proyek_akhir_kewirausahaan.data.repository.BookRepositoryImpl as LocalBookRepositoryImpl
import com.example.proyek_akhir_kewirausahaan.domain.usecase.GetAllBooksUseCase
import com.example.proyek_akhir_kewirausahaan.domain.usecase.GetUserProfileUseCase
import com.example.proyek_akhir_kewirausahaan.domain.usecase.SearchBooksUseCase
import com.example.proyek_akhir_kewirausahaan.domain.usecase.ToggleFavoriteUseCase
import com.example.proyek_akhir_kewirausahaan.model.repository.BookRepository
import com.example.proyek_akhir_kewirausahaan.model.repository.BookRepositoryImpl
import kotlinx.coroutines.runBlocking

class ReademViewModelFactory (
    private val context: Context,
    private val bookRepository: BookRepository = BookRepositoryImpl()
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReademViewModel::class.java)) {
            val database = AppDatabase.getDatabase(context)
            val localRepository = LocalBookRepositoryImpl(database.bookDao())

            runBlocking {
                localRepository.initializeDatabaseIfNeeded()
            }

            val getAllBooksUseCase = GetAllBooksUseCase(localRepository)
            val searchBooksUseCase = SearchBooksUseCase(localRepository)
            val toggleFavoriteUseCase = ToggleFavoriteUseCase(localRepository)
            val getUserProfileUseCase = GetUserProfileUseCase(bookRepository)
            val userPreferences = UserPreferences(context.applicationContext)

            @Suppress("UNCHECKED_CAST")
            return ReademViewModel(
                bookRepository,
                getAllBooksUseCase,
                searchBooksUseCase,
                toggleFavoriteUseCase,
                getUserProfileUseCase,
                userPreferences
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}