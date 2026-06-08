package com.example.proyek_akhir_kewirausahaan

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyek_akhir_kewirausahaan.viewmodel.ReademViewModel
import com.example.proyek_akhir_kewirausahaan.model.repository.BookRepositoryImpl
import com.example.proyek_akhir_kewirausahaan.ui.components.BottomNavigationBar
import com.example.proyek_akhir_kewirausahaan.ui.components.TopNavigationBar
import com.example.proyek_akhir_kewirausahaan.ui.screens.FeedScreen
import com.example.proyek_akhir_kewirausahaan.ui.screens.ProfileScreen
import com.example.proyek_akhir_kewirausahaan.ui.screens.SearchScreen
import com.example.proyek_akhir_kewirausahaan.ui.screens.SettingScreen
import com.example.proyek_akhir_kewirausahaan.ui.screens.SubscriptionScreen
import com.example.proyek_akhir_kewirausahaan.ui.screens.SupportScreen
import com.example.proyek_akhir_kewirausahaan.ui.theme.ProyekAkhirKewirausahaanTheme
import com.example.proyek_akhir_kewirausahaan.viewmodel.ReademViewModelFactory

enum class ReademScreen(val route: String) {
    Feed("feed"),
    Search("search"),
    Profile("profile"),
    Setting("settings"),
    Library("library"),
    Subscription("subscription"),
    Support("support")
}

@Composable
fun ReademApp() {
    val factory = remember {
        ReademViewModelFactory(BookRepositoryImpl())
    }

    val viewModel: ReademViewModel = viewModel(factory = factory)
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val uiState by viewModel.uiState.collectAsState()

    val onFontSizeChange: (Float) -> Unit = viewModel::updateFontSize
    val onDailyRemindersToggle: (Boolean) -> Unit = viewModel::toggleDailyReminders
    val onNewReleasesToggle: (Boolean) -> Unit = viewModel::toggleNewReleases

    val currentScreen = ReademScreen.entries.find {
        it.route == currentRoute
    } ?: ReademScreen.Feed


    val showBottomBar = when (currentScreen) {
        ReademScreen.Feed,
        ReademScreen.Profile,
        ReademScreen.Setting,
        ReademScreen.Search,
        ReademScreen.Library -> true
        ReademScreen.Subscription,
        ReademScreen.Support -> false
    }

    val showTopBar = when (currentScreen) {
        ReademScreen.Feed,
        ReademScreen.Profile,
        ReademScreen.Setting,
        ReademScreen.Library -> true
        ReademScreen.Search,
        ReademScreen.Subscription,
        ReademScreen.Support -> false
    }

    val showSearchTopBar = when (currentScreen) {
        ReademScreen.Feed,
        ReademScreen.Profile,
        ReademScreen.Setting -> true
        else -> false
    }

    ProyekAkhirKewirausahaanTheme(fontSize = uiState.fontSize) {
        Scaffold(
            modifier = Modifier.systemBarsPadding(),

            topBar = {
                if (showTopBar) {
                    TopNavigationBar(
                        showSearch = showSearchTopBar,
                        onSearchClick = {
                            navController.navigate(ReademScreen.Search.route)
                        }
                    )
                }
            },

            bottomBar = {
                if (showBottomBar) {
                    BottomNavigationBar(
                        currentScreen,
                        onScreenSelected = { screen ->
                            navController.navigate(screen.route)
                        }
                    )
                }
            }
        ) { padding ->
            NavHost(
                modifier = Modifier.padding(padding),
                navController = navController,
                startDestination = ReademScreen.Feed.route
            ) {
                composable(ReademScreen.Feed.route) { FeedScreen(uiState) }
                composable(ReademScreen.Search.route) {
                    SearchScreen(
                        uiState = uiState,
                        onBackClick = { navController.popBackStack() }
                    )
                }
                composable(ReademScreen.Setting.route) {
                    SettingScreen(
                        uiState = uiState,
                        onFontSizeChange = onFontSizeChange,
                        onDailyRemindersToggle = onDailyRemindersToggle,
                        onNewReleasesToggle = onNewReleasesToggle,
                        onManagePlanClick = { navController.navigate(ReademScreen.Subscription.route) },
                        onSupportClick = { navController.navigate(ReademScreen.Support.route) }
                    )
                }
                composable(ReademScreen.Subscription.route) {
                    SubscriptionScreen(
                        onBackClick = { navController.popBackStack() }
                    )
                }
                composable(ReademScreen.Support.route) {
                    SupportScreen(
                        onBackClick = { navController.popBackStack() }
                    )
                }
                composable(ReademScreen.Profile.route) {
                    ProfileScreen(
                        Modifier,
                        uiState
                    )
                }
                composable(ReademScreen.Library.route) { /* screen malik */ }
            }
        }
    }
}
