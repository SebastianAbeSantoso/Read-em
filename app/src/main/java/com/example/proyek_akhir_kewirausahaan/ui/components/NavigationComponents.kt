package com.example.proyek_akhir_kewirausahaan.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyek_akhir_kewirausahaan.ReademScreen

@Composable
fun BottomNavigationBar(
    currentScreen: ReademScreen,
    onScreenSelected: (ReademScreen) -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationItem(
                icon = Icons.AutoMirrored.Filled.LibraryBooks,
                label = "LIBRARY",
                selected = currentScreen == ReademScreen.Library,
                onClick = { onScreenSelected(ReademScreen.Library) }
            )
            NavigationItem(
                icon = Icons.Default.Explore,
                label = "EXPLORE",
                selected = currentScreen == ReademScreen.Feed,
                onClick = { onScreenSelected(ReademScreen.Feed) }
            )
            NavigationItem(
                icon = Icons.Default.AccountCircle,
                label = "ACCOUNT",
                selected = currentScreen == ReademScreen.Profile,
                onClick = { onScreenSelected(ReademScreen.Profile) }
            )
            NavigationItem(
                icon = Icons.Default.Settings,
                label = "SETTINGS",
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
                color = Color(0xFFE57373),
                fontWeight = FontWeight.Bold
            )

            if (showSearch) {
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun NavigationItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    AnimatedContent(
        targetState = selected,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
        label = "NavItemAnimation",
        modifier = Modifier.padding(2.dp)
    ) { isSelected ->
        if (isSelected) {
            Box(
                modifier = Modifier
                    .height(64.dp)
                    .width(104.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFE57373), Color(0xFFD32F2F))
                        ),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .clickable { onClick() },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = label,
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .clickable { onClick() }
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = label,
                    color = Color.Gray,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                    fontWeight = FontWeight.Bold
                )
            }
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
