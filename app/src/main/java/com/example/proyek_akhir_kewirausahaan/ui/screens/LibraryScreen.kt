package com.example.proyek_akhir_kewirausahaan.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyek_akhir_kewirausahaan.model.DataSource
import com.example.proyek_akhir_kewirausahaan.model.data.BookData
import com.example.proyek_akhir_kewirausahaan.model.data.ReadSessionData
import com.example.proyek_akhir_kewirausahaan.model.data.ReadStatus
import com.example.proyek_akhir_kewirausahaan.ui.theme.AccentSalmon
import com.example.proyek_akhir_kewirausahaan.ui.theme.BackgroundDark
import com.example.proyek_akhir_kewirausahaan.ui.theme.SurfaceDark
import com.example.proyek_akhir_kewirausahaan.ui.theme.TextPrimary
import com.example.proyek_akhir_kewirausahaan.ui.theme.TextSecondary
import com.example.proyek_akhir_kewirausahaan.viewmodel.ReademUiState

private enum class LibraryTab { RECENTLY_OPENED, IN_PROGRESS }

@Composable
fun LibraryScreen(
    uiState: ReademUiState,
    onBookClick: (String) -> Unit = {},
    onContinueReading: (String) -> Unit = {}
) {
    val sessions = DataSource.readSessions
    val allBooks = DataSource.books

    var activeTab by remember { mutableStateOf(LibraryTab.RECENTLY_OPENED) }

    val recentlyOpened: List<Pair<BookData, ReadSessionData>> = remember(sessions, allBooks) {
        sessions
            .sortedByDescending { it.lastReadAt }
            .mapNotNull { session ->
                allBooks.find { it.id == session.bookId }?.let { book -> book to session }
            }
    }

    val inProgress: List<Pair<BookData, ReadSessionData>> = remember(sessions, allBooks) {
        sessions
            .filter { it.status == ReadStatus.IN_PROGRESS }
            .sortedByDescending { it.lastReadAt }
            .mapNotNull { session ->
                allBooks.find { it.id == session.bookId }?.let { book -> book to session }
            }
    }

    val displayedList = if (activeTab == LibraryTab.RECENTLY_OPENED) recentlyOpened else inProgress
    val featuredPair  = displayedList.firstOrNull()
    val restList      = if (displayedList.size > 1) displayedList.drop(1) else emptyList()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
                Text(
                    text  = "YOUR COLLECTION",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary,
                    letterSpacing = 2.sp
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text       = "Current Reads",
                    style      = MaterialTheme.typography.headlineLarge,
                    color      = TextPrimary,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LibraryTabChip(
                    label    = "Recently Opened",
                    selected = activeTab == LibraryTab.RECENTLY_OPENED,
                    onClick  = { activeTab = LibraryTab.RECENTLY_OPENED }
                )
                LibraryTabChip(
                    label    = "In Progress (${inProgress.size})",
                    selected = activeTab == LibraryTab.IN_PROGRESS,
                    onClick  = { activeTab = LibraryTab.IN_PROGRESS }
                )
            }
        }

        if (featuredPair != null) {
            item {
                FeaturedBookCard(
                    book              = featuredPair.first,
                    session           = featuredPair.second,
                    onContinueReading = onContinueReading,
                    modifier          = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(Modifier.height(12.dp))
            }
        }

        items(restList) { (book, session) ->
            CompactBookCard(
                book        = book,
                session     = session,
                onBookClick = onBookClick,
                modifier    = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 10.dp)
            )
        }

        if (displayedList.isEmpty()) {
            item {
                EmptyLibraryState(
                    tab      = activeTab,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 48.dp)
                )
            }
        }
    }
}

@Composable
private fun LibraryTabChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bgColor by animateColorAsState(
        targetValue   = if (selected) AccentSalmon else SurfaceDark,
        animationSpec = tween(200),
        label         = "tabBg"
    )
    val textColor by animateColorAsState(
        targetValue   = if (selected) Color.Black else TextSecondary,
        animationSpec = tween(200),
        label         = "tabText"
    )
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text       = label,
            color      = textColor,
            style      = MaterialTheme.typography.labelMedium,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
private fun FeaturedBookCard(
    book: BookData,
    session: ReadSessionData,
    onContinueReading: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val progressPercent = session.progressPercent
    val pctLabel        = "${(progressPercent * 100).toInt()}% COMPLETED"

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Image(
            painter            = painterResource(id = book.coverResId),
            contentDescription = book.title,
            contentScale       = ContentScale.Crop,
            modifier           = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.85f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(AccentSalmon.copy(alpha = 0.9f))
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(
                    text       = pctLabel,
                    color      = Color.Black,
                    style      = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text       = book.title,
                style      = MaterialTheme.typography.headlineSmall,
                color      = Color.White,
                fontWeight = FontWeight.Bold,
                maxLines   = 2,
                overflow   = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text     = book.synopsis,
                style    = MaterialTheme.typography.bodySmall,
                color    = Color.White.copy(alpha = 0.75f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(12.dp))

            LinearProgressIndicator(
                progress   = { progressPercent },
                modifier   = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color      = AccentSalmon,
                trackColor = Color.White.copy(alpha = 0.2f)
            )

            Spacer(Modifier.height(12.dp))

            Button(
                onClick  = { onContinueReading(book.id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape  = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AccentSalmon,
                    contentColor   = Color.Black
                )
            ) {
                Icon(
                    imageVector        = Icons.Default.PlayArrow,
                    contentDescription = null,
                    modifier           = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text          = "CONTINUE READING",
                    fontWeight    = FontWeight.Bold,
                    style         = MaterialTheme.typography.labelMedium,
                    letterSpacing = 1.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun CompactBookCard(
    book: BookData,
    session: ReadSessionData,
    onBookClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceDark)
            .clickable { onBookClick(book.id) }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 70.dp, height = 90.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Image(
                painter            = painterResource(id = book.coverResId),
                contentDescription = book.title,
                contentScale       = ContentScale.Crop,
                modifier           = Modifier.fillMaxSize()
            )
            LinearProgressIndicator(
                progress   = { session.progressPercent },
                modifier   = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .align(Alignment.BottomCenter),
                color      = AccentSalmon,
                trackColor = Color.Black.copy(alpha = 0.4f)
            )
            if (session.progressPercent == 0f) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(AccentSalmon)
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Text(
                        text  = "NEW",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 8.sp
                    )
                }
            }
        }

        Spacer(Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text       = book.title,
                style      = MaterialTheme.typography.titleMedium,
                color      = TextPrimary,
                fontWeight = FontWeight.Bold,
                maxLines   = 2,
                overflow   = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text  = book.author,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }
    }
}

@Composable
private fun EmptyLibraryState(
    tab: LibraryTab,
    modifier: Modifier = Modifier
) {
    Column(
        modifier            = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text       = if (tab == LibraryTab.IN_PROGRESS) "Nothing in progress" else "No books opened yet",
            style      = MaterialTheme.typography.titleMedium,
            color      = TextPrimary,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text  = if (tab == LibraryTab.IN_PROGRESS)
                "Books you're actively reading will appear here."
            else
                "Open a book from Explore to start your collection.",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary
        )
    }
}