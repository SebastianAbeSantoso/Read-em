package com.example.proyek_akhir_kewirausahaan.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
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
    onNewReleasesToggle: (Boolean) -> Unit,
    onManagePlanClick: () -> Unit = {},
    onSupportClick: () -> Unit = {},
    onUserNameChange: (String) -> Unit = {},
    onAvatarUriChange: (String) -> Unit = {}
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
        AccountCard(onManagePlanClick)

        Spacer(modifier = Modifier.height(8.dp))

        ProfilePicturePreference(
            avatarUri = uiState.userProfile?.avatarUri,
            onAvatarUriChange = onAvatarUriChange
        )

        Spacer(modifier = Modifier.height(8.dp))

        DisplayNamePreference(
            name = uiState.userProfile?.name ?: "",
            onNameConfirm = onUserNameChange
        )

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

        SupportHelpCard(onSupportClick)

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
fun AccountCard(onManagePlanClick: () -> Unit) {
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
                onClick = onManagePlanClick,
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
fun ProfilePicturePreference(
    avatarUri: String?,
    onAvatarUriChange: (String) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            onAvatarUriChange(uri.toString())
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2C2C2C)),
                contentAlignment = Alignment.Center
            ) {
                if (avatarUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(avatarUri),
                        contentDescription = "Profile picture",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(Icons.Default.Person, contentDescription = null, tint = AccentSalmon)
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Profile Picture", fontWeight = FontWeight.SemiBold)
                Text(text = "Tap to choose from gallery", color = TextSecondary, fontSize = 12.sp)
            }

            Button(
                onClick = {
                    launcher.launch(
                        androidx.activity.result.PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = AccentSalmon),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Change", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayNamePreference(name: String, onNameConfirm: (String) -> Unit) {
    var draft by remember(name) { mutableStateOf(name) }
    val changed = draft.trim() != name && draft.isNotBlank()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Person, contentDescription = null, tint = AccentSalmon)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Display Name", fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = draft,
                onValueChange = { draft = it },
                singleLine = true,
                placeholder = { Text("Your name", color = TextSecondary) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF2C2C2C),
                    unfocusedContainerColor = Color(0xFF2C2C2C),
                    cursorColor = AccentSalmon,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { onNameConfirm(draft.trim()) },
                enabled = changed,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AccentSalmon,
                    disabledContainerColor = Color(0xFF2C2C2C)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Save Name",
                    color = if (changed) Color.White else TextSecondary,
                    fontWeight = FontWeight.Bold
                )
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
fun SupportHelpCard(onSupportClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp),
        onClick = onSupportClick
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