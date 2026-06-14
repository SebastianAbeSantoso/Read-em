package com.example.proyek_akhir_kewirausahaan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.proyek_akhir_kewirausahaan.domain.model.Book
import com.example.proyek_akhir_kewirausahaan.model.data.UserProfileData
import com.example.proyek_akhir_kewirausahaan.ui.theme.AccentSalmon
import com.example.proyek_akhir_kewirausahaan.ui.theme.ProyekAkhirKewirausahaanTheme
import com.example.proyek_akhir_kewirausahaan.viewmodel.ReademUiState
import java.util.Locale
import com.example.proyek_akhir_kewirausahaan.model.DataSource

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    uiState: ReademUiState,
    onProfilePictureClick: () -> Unit = {}
) {
    val profile = uiState.userProfile

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0C0908))
            .padding(horizontal = 20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))
            AvatarSection(
                avatarUri = profile?.avatarUri,
                onProfilePictureClick = onProfilePictureClick
            )
            Spacer(modifier = Modifier.height(24.dp))
            UserInfoSection(profile)
            Spacer(modifier = Modifier.height(32.dp))
            ActiveStreakCard(streakDays = profile?.streakDays ?: 0)
            Spacer(modifier = Modifier.height(16.dp))
            XpCountCard(
                level = profile?.level ?: 0,
                weeklyXp = profile?.weeklyXp ?: emptyList()
            )
            Spacer(modifier = Modifier.height(32.dp))
            CredentialsSection(credentials = profile?.credentials ?: emptyList())
            Spacer(modifier = Modifier.height(32.dp))
            ArchiveBreakdownSection(uiState.books)
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun AvatarSection(
    avatarUri: String?,
    onProfilePictureClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(160.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(Color(0xFF231F1E))
                .clickable { onProfilePictureClick() }
        ) {
            if (avatarUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(avatarUri),
                    contentDescription = "Profile picture",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .size(24.dp)
                    .background(Color(0xFFE57373), RoundedCornerShape(4.dp)),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier.size(12.dp).background(Color.White.copy(alpha = 0.8f), CircleShape))
            }
        }

        Box(
            modifier = Modifier
                .size(160.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(Color(0xFF231F1E))
        )
    }
}

@Composable
fun UserInfoSection(profile: UserProfileData?) {
    Column {
        Surface(
            color = Color(0xFF231F1E),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = (profile?.rankTitle ?: "ARCHIVIST").uppercase(),
                color = Color(0xFF8E8E8E),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = (profile?.name ?: "Reader"),
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            letterSpacing = (-1).sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = formatCount(profile?.totalBooksRead ?: 0),
                color = AccentSalmon,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = " Books",
                color = Color(0xFF8E8E8E),
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = formatCount(profile?.totalNotes ?: 0),
                color = AccentSalmon,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = " Notes",
                color = Color(0xFF8E8E8E),
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 2.dp)
            )
        }
    }
}

@Composable
fun ActiveStreakCard(streakDays: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF161312)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(modifier = Modifier.padding(24.dp).fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .size(width = 80.dp, height = 100.dp)
                    .align(Alignment.BottomEnd)
                    .background(Color(0xFF1E1A19), RoundedCornerShape(8.dp))
            )

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "ACTIVE STREAK",
                        color = Color(0xFF6E6E6E),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(modifier = Modifier.size(20.dp).background(AccentSalmon, CircleShape))
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = streakDays.toString(),
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    lineHeight = 64.sp
                )

                Text(
                    text = "Consecutive Days",
                    color = AccentSalmon,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun XpCountCard(level: Int, weeklyXp: List<Int>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF161312)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "XP COUNT",
                color = Color(0xFF6E6E6E),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "LEVEL $level",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))

            val maxXp = (weeklyXp.maxOrNull() ?: 0).coerceAtLeast(1)
            val maxBarHeight = 140f
            val minBarHeight = 40f

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                if (weeklyXp.isEmpty()) {
                    repeat(4) { i ->
                        XpBar(height = 50.dp, label = "WK 0${i + 1}", active = i != 0)
                    }
                } else {
                    weeklyXp.forEachIndexed { index, xp ->
                        val ratio = xp.toFloat() / maxXp.toFloat()
                        val h = (minBarHeight + ratio * (maxBarHeight - minBarHeight)).dp
                        XpBar(
                            height = h,
                            label = "WK 0${index + 1}",
                            active = xp > 0
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun XpBar(height: Dp, label: String, active: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .width(64.dp)
                .height(height)
                .then(
                    if (active) {
                        Modifier.background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF231F1E), Color(0xFFE57373))
                            ),
                            shape = RoundedCornerShape(6.dp)
                        )
                    } else {
                        Modifier.background(
                            color = Color(0xFF231F1E),
                            shape = RoundedCornerShape(6.dp)
                        )
                    }
                )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = label, color = Color(0xFF4E4E4E), fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CredentialsSection(credentials: List<String>) {
    Column {
        Text(
            text = "Credentials",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(24.dp))

        val rows = credentials.chunked(3)
        rows.forEachIndexed { rowIndex, rowCreds ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                rowCreds.forEach { name ->
                    CredentialItem(name = name)
                }
                if (rowIndex == rows.lastIndex && rowCreds.size < 3) {
                    AddCredentialSlot()
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        if (credentials.isEmpty()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                AddCredentialSlot()
            }
        }
    }
}

@Composable
fun AddCredentialSlot() {
    Box(
        modifier = Modifier.size(width = 100.dp, height = 140.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .border(1.dp, Color(0xFF231F1E), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("+", color = Color(0xFF231F1E), fontSize = 32.sp)
        }
    }
}

@Composable
fun CredentialItem(name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(100.dp)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color(0xFF161312), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.size(40.dp).background(AccentSalmon.copy(alpha = 0.2f), CircleShape))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = name,
            color = Color(0xFF6E6E6E),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ArchiveBreakdownSection(books: List<Book>) {
    Column {
        Text(
            text = "Archive Breakdown",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(24.dp))

        val displayBooks = books.ifEmpty {
            DataSource.books.take(4).map { it.toProfileBook() }
        }

        displayBooks.chunked(2).forEach { rowBooks ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                rowBooks.forEach { book ->
                    ArchiveBookItem(book, modifier = Modifier.weight(1f))
                }
                if (rowBooks.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun ArchiveBookItem(book: Book, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.75f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF161312))
        ) {
            if (book.title == "Silent Horizon") {
                Surface(
                    color = Color(0xFFE57373),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "FINISHED",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = book.title,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )

        Text(
            text = book.genres.firstOrNull() ?: "",
            color = Color(0xFF6E6E6E),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun formatCount(value: Int): String {
    return when {
        value >= 1000 && value % 1000 == 0 -> "${value / 1000}k"
        value >= 1000 -> String.format(Locale.US, "%,d", value)
        else -> value.toString()
    }
}

private fun com.example.proyek_akhir_kewirausahaan.model.data.BookData.toProfileBook(): Book {
    return Book(
        id = id,
        title = title,
        author = author,
        coverResId = coverResId,
        genres = genres,
        synopsis = synopsis,
        totalChapters = totalChapters,
        readingTimeMin = readingTimeMin,
        rating = rating,
        readersCount = readersCount,
        isPremium = isPremium
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF0C0908, heightDp = 1500)
@Composable
fun ProfileScreenPreview() {
    ProyekAkhirKewirausahaanTheme {
        Surface(color = Color(0xFF0C0908)) {
            ProfileScreen(
                uiState = ReademUiState(
                    userProfile = UserProfileData(
                        id = "user_local",
                        name = "Gojo Satoru",
                        avatarRes = 0,
                        secondaryAvatarRes = 0,
                        rankTitle = "Premium Archivist",
                        level = 42,
                        xp = 12450,
                        xpToNextLevel = 15000,
                        streakDays = 48,
                        totalBooksRead = 1204,
                        totalNotes = 84000,
                        credentials = listOf("POLYMATH", "DEEP THINKER", "SCRIBE", "CURATOR"),
                        weeklyXp = listOf(200, 800, 500, 1000)
                    ),
                    books = DataSource.books.take(4).map { it.toProfileBook() }
                )
            )
        }
    }
}