package com.example.proyek_akhir_kewirausahaan.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyek_akhir_kewirausahaan.ReademScreen
import com.example.proyek_akhir_kewirausahaan.model.data.BookData
import com.example.proyek_akhir_kewirausahaan.model.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReademViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _reademState = MutableStateFlow(ReademScreen.Feed)
    val reademState = _reademState.asStateFlow()

    private val _books = MutableStateFlow<List<BookData>>(emptyList())
    val books = _books.asStateFlow()

    init {
        _books.value = bookRepository.getAllBooks()
    }
}