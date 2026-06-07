package com.example.proyek_akhir_kewirausahaan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyek_akhir_kewirausahaan.model.data.BookData
import com.example.proyek_akhir_kewirausahaan.ui.theme.AccentSalmon
import com.example.proyek_akhir_kewirausahaan.ui.theme.ProyekAkhirKewirausahaanTheme
import com.example.proyek_akhir_kewirausahaan.ui.theme.TextSecondary
import com.example.proyek_akhir_kewirausahaan.viewmodel.ReademUiState

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    uiState: ReademUiState
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0C0908))
            .padding(horizontal = 20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))
            AvatarSection()
            Spacer(modifier = Modifier.height(24.dp))
            UserInfoSection()
            Spacer(modifier = Modifier.height(32.dp))
            ActiveStreakCard()
            Spacer(modifier = Modifier.height(16.dp))
            XpCountCard()
            Spacer(modifier = Modifier.height(32.dp))
            CredentialsSection()
            Spacer(modifier = Modifier.height(32.dp))
            ArchiveBreakdownSection(uiState.books)
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun AvatarSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(160.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(Color(0xFF231F1E))
        ) {
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
fun UserInfoSection() {
    Column {
        Surface(
            color = Color(0xFF231F1E),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "PREMIUM ARCHIVIST",
                color = Color(0xFF8E8E8E),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "GOJO SATORU",
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            letterSpacing = (-1).sp
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "1,204",
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
                text = "84k",
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
fun ActiveStreakCard() {
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
                    text = "48",
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
fun XpCountCard() {
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
                text = "LEVEL 42",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                XpBar(height = 50.dp, label = "WK 01", active = false)
                XpBar(height = 120.dp, label = "WK 02", active = true)
                XpBar(height = 80.dp, label = "WK 03", active = true)
                XpBar(height = 140.dp, label = "WK 04", active = true)
            }
        }
    }
}

@Composable
fun XpBar(height: androidx.compose.ui.unit.Dp, label: String, active: Boolean) {
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
fun CredentialsSection() {
    Column {
        Text(
            text = "Credentials",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CredentialItem(name = "POLYMATH")
            CredentialItem(name = "DEEP THINKER")
            CredentialItem(name = "SCRIBE")
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            CredentialItem(name = "CURATOR")
            Box(
                modifier = Modifier
                    .size(width = 100.dp, height = 140.dp),
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
fun ArchiveBreakdownSection(books: List<BookData>) {
    Column {
        Text(
            text = "Archive Breakdown",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        val displayBooks = books.ifEmpty { 
            listOf(
                BookData("1", "Silent Horizon", "Author", 0, listOf("SCIENCE FICTION"), "", 0, 0, 0.0, 0),
                BookData("2", "The Lost Archive", "Author", 0, listOf("MYSTERY"), "", 0, 0, 0.0, 0),
                BookData("3", "Digital Breath", "Author", 0, listOf("CYBERPUNK"), "", 0, 0, 0.0, 0),
                BookData("4", "Kinetic Dreams", "Author", 0, listOf("POETRY"), "", 0, 0, 0.0, 0)
            )
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
fun ArchiveBookItem(book: BookData, modifier: Modifier = Modifier) {
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

@Preview(showBackground = true, backgroundColor = 0xFF0C0908, heightDp = 1500)
@Composable
fun ProfileScreenPreview() {
    ProyekAkhirKewirausahaanTheme {
        Surface(color = Color(0xFF0C0908)) {
            ProfileScreen(
                uiState = ReademUiState(
                    books = listOf(
                        BookData("1", "Silent Horizon", "Author", 0, listOf("SCIENCE FICTION"), "", 0, 0, 0.0, 0),
                        BookData("2", "The Lost Archive", "Author", 0, listOf("MYSTERY"), "", 0, 0, 0.0, 0),
                        BookData("3", "Digital Breath", "Author", 0, listOf("CYBERPUNK"), "", 0, 0, 0.0, 0),
                        BookData("4", "Kinetic Dreams", "Author", 0, listOf("POETRY"), "", 0, 0, 0.0, 0)
                    )
                )
            )
        }
    }
}
