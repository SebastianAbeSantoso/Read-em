package com.example.proyek_akhir_kewirausahaan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyek_akhir_kewirausahaan.viewmodel.ReademUiState
import com.example.proyek_akhir_kewirausahaan.ui.theme.AccentSalmon
import com.example.proyek_akhir_kewirausahaan.ui.theme.GradientEnd
import com.example.proyek_akhir_kewirausahaan.ui.theme.GradientStart
import com.example.proyek_akhir_kewirausahaan.ui.theme.ProyekAkhirKewirausahaanTheme
import com.example.proyek_akhir_kewirausahaan.ui.theme.TextSecondary

@Composable
fun SettingScreen(
    uiState: ReademUiState,
    onFontSizeChange: (Float) -> Unit,
    onDailyRemindersToggle: (Boolean) -> Unit,
    onNewReleasesToggle: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Read'em",
            style = MaterialTheme.typography.headlineMedium,
            color = AccentSalmon,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        SectionHeader(title = "Account")
        AccountCard()

        Spacer(modifier = Modifier.height(24.dp))

        SectionHeader(title = "Preferences")
        FontSizePreference(
            fontSize = uiState.fontSize,
            onFontSizeChange = onFontSizeChange
        )

        Spacer(modifier = Modifier.height(24.dp))

        NotificationsSection(
            dailyReminders = uiState.dailyReminders,
            onDailyRemindersToggle = onDailyRemindersToggle,
            newReleases = uiState.newReleases,
            onNewReleasesToggle = onNewReleasesToggle
        )

        Spacer(modifier = Modifier.height(24.dp))

        SupportHelpCard()

        Spacer(modifier = Modifier.height(24.dp))

        SystemManifesto()
    }
}

@Composable
fun SectionHeader(title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .width(20.dp)
                .height(2.dp)
                .background(AccentSalmon)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AccountCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Subscription", color = TextSecondary, fontSize = 12.sp)
            Text(text = "PRO MEMBER", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = "Next billing: Oct 12, 2024", color = TextSecondary, fontSize = 12.sp)
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(GradientStart, GradientEnd)
                            )
                        )
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "MANAGE PLAN", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun FontSizePreference(fontSize: Float, onFontSizeChange: (Float) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.FormatSize, contentDescription = null, tint = AccentSalmon)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Font Size", fontWeight = FontWeight.SemiBold)
            }
            
            Slider(
                value = fontSize,
                onValueChange = onFontSizeChange,
                valueRange = 12f..32f,
                colors = SliderDefaults.colors(
                    thumbColor = AccentSalmon,
                    activeTrackColor = AccentSalmon
                )
            )
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Small", fontSize = 10.sp, color = TextSecondary)
                Text(text = "${fontSize.toInt()}pt", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(text = "Large", fontSize = 14.sp, color = TextSecondary)
            }
        }
    }
}

@Composable
fun NotificationsSection(
    dailyReminders: Boolean,
    onDailyRemindersToggle: (Boolean) -> Unit,
    newReleases: Boolean,
    onNewReleasesToggle: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Notifications, contentDescription = null, tint = AccentSalmon)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Notifications", fontWeight = FontWeight.SemiBold)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Daily Reminders", fontSize = 14.sp)
                Switch(
                    checked = dailyReminders,
                    onCheckedChange = onDailyRemindersToggle,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = AccentSalmon,
                        uncheckedThumbColor = Color.Gray,
                        uncheckedTrackColor = Color.Transparent,
                        uncheckedBorderColor = Color.Gray
                    )
                )
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "New Releases", fontSize = 14.sp)
                Switch(
                    checked = newReleases,
                    onCheckedChange = onNewReleasesToggle,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = AccentSalmon,
                        uncheckedThumbColor = Color.Gray,
                        uncheckedTrackColor = Color.Transparent,
                        uncheckedBorderColor = Color.Gray
                    )
                )
            }
        }
    }
}

@Composable
fun SupportHelpCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF2C2C2C), shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.HelpOutline,
                    contentDescription = null,
                    tint = AccentSalmon,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = "Support & Help",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Get in touch with the archivist team",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
fun SystemManifesto() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "SYSTEM MANIFESTO",
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Read'em is a kinetic digital repository designed for the focused reader. All settings are ephemeral and stored locally for your immediate archival needs.",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary,
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp),
            lineHeight = 16.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "v2.4.0",
            style = MaterialTheme.typography.headlineSmall,
            color = TextSecondary,
            fontWeight = FontWeight.Light
        )
        Text(
            text = "CORE ENGINE STABILIZED",
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary,
            fontSize = 8.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF121212, heightDp = 1000)
@Composable
fun SettingScreenPreview() {
    ProyekAkhirKewirausahaanTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SettingScreen(
                uiState = ReademUiState(),
                onFontSizeChange = {},
                onDailyRemindersToggle = {},
                onNewReleasesToggle = {}
            )
        }
    }
}
