package com.example.proyek_akhir_kewirausahaan

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyek_akhir_kewirausahaan.viewmodel.ReademViewModel
import com.example.proyek_akhir_kewirausahaan.model.repository.BookRepositoryImpl
import com.example.proyek_akhir_kewirausahaan.ui.screens.MainScreen
import com.example.proyek_akhir_kewirausahaan.viewmodel.ReademViewModelFactory

enum class ReademScreen{
    Feed,
    Search,
    Profile,
    Setting
}

@Composable
fun ReademApp() {
    val factory = remember {
        ReademViewModelFactory(BookRepositoryImpl())
    }

    val viewModel: ReademViewModel = viewModel(factory = factory)
    val navController = rememberNavController()

    val books by viewModel.books.collectAsState()

    NavHost(navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController, viewModel)
        }
    }

    // Navigators (Navigate through each screens using enum, not hardcoded strings
}