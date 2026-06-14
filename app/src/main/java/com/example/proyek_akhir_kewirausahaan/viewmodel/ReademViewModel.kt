package com.example.proyek_akhir_kewirausahaan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_akhir_kewirausahaan.data.local.UserPreferences
import com.example.proyek_akhir_kewirausahaan.domain.usecase.GetAllBooksUseCase
import com.example.proyek_akhir_kewirausahaan.domain.usecase.GetUserProfileUseCase
import com.example.proyek_akhir_kewirausahaan.domain.usecase.SearchBooksUseCase
import com.example.proyek_akhir_kewirausahaan.domain.usecase.ToggleFavoriteUseCase
import com.example.proyek_akhir_kewirausahaan.model.repository.BookRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class ReademViewModel(
    private val bookRepository: BookRepository,
    private val getAllBooksUseCase: GetAllBooksUseCase,
    private val searchBooksUseCase: SearchBooksUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReademUiState())
    val uiState: StateFlow<ReademUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")

    init {
        _uiState.update { it.copy(userProfile = getUserProfileUseCase()) }

        viewModelScope.launch {
            val savedName = userPreferences.userName.first()
            val savedUri = userPreferences.avatarUri.first()
            _uiState.update { state ->
                state.copy(
                    userProfile = state.userProfile?.copy(
                        name = savedName ?: state.userProfile.name,
                        avatarUri = savedUri ?: state.userProfile.avatarUri
                    )
                )
            }
        }

        _searchQuery
            .debounce(300.milliseconds)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                if (query.isEmpty()) {
                    getAllBooksUseCase()
                } else {
                    searchBooksUseCase(query)
                }
            }
            .onEach { books ->
                _uiState.update { it.copy(books = books) }
            }
            .launchIn(viewModelScope)
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun toggleFavorite(bookId: String) {
        viewModelScope.launch {
            toggleFavoriteUseCase(bookId)
        }
    }

    fun updateUserName(newName: String) {
        _uiState.update { state ->
            state.copy(userProfile = state.userProfile?.copy(name = newName))
        }
        viewModelScope.launch {
            userPreferences.setUserName(newName)
        }
    }

    fun updateAvatarUri(uri: String) {
        _uiState.update { state ->
            state.copy(userProfile = state.userProfile?.copy(avatarUri = uri))
        }
        viewModelScope.launch {
            userPreferences.setAvatarUri(uri)
        }
    }

    fun updateFontSize(size: Float) {
        _uiState.update { it.copy(fontSize = size) }
    }

    fun toggleDailyReminders(enabled: Boolean) {
        _uiState.update { it.copy(dailyReminders = enabled) }
    }

    fun toggleNewReleases(enabled: Boolean) {
        _uiState.update { it.copy(newReleases = enabled) }
    }

    fun getFirstChapter(bookId: String) = bookRepository.getChapters(bookId).firstOrNull { it.number == 1 }
}