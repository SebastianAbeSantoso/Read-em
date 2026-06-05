package com.example.proyek_akhir_kewirausahaan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyek_akhir_kewirausahaan.ReademScreen
import com.example.proyek_akhir_kewirausahaan.ui.theme.AccentSalmon
import com.example.proyek_akhir_kewirausahaan.viewmodel.ReademViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: ReademViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                currentScreen = uiState.currentScreen,
                onScreenSelected = { viewModel.navigateTo(it) }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (uiState.currentScreen) {
                ReademScreen.Feed -> LibraryPlaceholder()
                ReademScreen.Search -> ScreenPlaceholder("Search")
                ReademScreen.Profile -> ScreenPlaceholder("Profile")
                ReademScreen.Setting -> SettingScreen(
                    uiState = uiState,
                    onFontSizeChange = { viewModel.updateFontSize(it) },
                    onDailyRemindersToggle = { viewModel.toggleDailyReminders(it) },
                    onNewReleasesToggle = { viewModel.toggleNewReleases(it) }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    currentScreen: ReademScreen,
    onScreenSelected: (ReademScreen) -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationItem(
                icon = Icons.Default.CollectionsBookmark,
                selected = currentScreen == ReademScreen.Feed,
                onClick = { onScreenSelected(ReademScreen.Feed) }
            )
            NavigationItem(
                icon = Icons.Default.Search,
                selected = currentScreen == ReademScreen.Search,
                onClick = { onScreenSelected(ReademScreen.Search) }
            )
            NavigationItem(
                icon = Icons.Default.Person,
                selected = currentScreen == ReademScreen.Profile,
                onClick = { onScreenSelected(ReademScreen.Profile) }
            )
            NavigationItem(
                icon = Icons.Default.Settings,
                selected = currentScreen == ReademScreen.Setting,
                onClick = { onScreenSelected(ReademScreen.Setting) }
            )
        }
    }
}

@Composable
fun NavigationItem(
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selected) AccentSalmon else Color.Gray,
            modifier = Modifier.size(28.dp)
        )
        if (selected) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .background(AccentSalmon, shape = androidx.compose.foundation.shape.CircleShape)
            )
        }
    }
}

@Composable
fun LibraryPlaceholder() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Library Content Placeholder")
    }
}

@Composable
fun ScreenPlaceholder(title: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "$title Placeholder")
    }
}
