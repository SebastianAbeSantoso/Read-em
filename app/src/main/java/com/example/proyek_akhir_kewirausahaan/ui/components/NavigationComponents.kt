package com.example.proyek_akhir_kewirausahaan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.proyek_akhir_kewirausahaan.ReademScreen
import com.example.proyek_akhir_kewirausahaan.ui.theme.AccentSalmon

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
fun TopNavigationBar(
    showSearch: Boolean,
    onSearchClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Read'em",
                style = MaterialTheme.typography.headlineMedium,
                color = AccentSalmon,
                fontWeight = FontWeight.Bold
            )

            if (showSearch) {
                NavigationItem(
                    icon = Icons.Default.Search,
                    onClick = onSearchClick
                )
            }
        }
    }
}

@Composable
fun NavigationItem(
    icon: ImageVector,
    selected: Boolean = false,
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
