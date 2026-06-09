package com.example.proyek_akhir_kewirausahaan.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.proyek_akhir_kewirausahaan.ui.theme.AccentSalmon
import com.example.proyek_akhir_kewirausahaan.ui.theme.ProyekAkhirKewirausahaanTheme
import com.example.proyek_akhir_kewirausahaan.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportScreen(
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Support & Help", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFF121212)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "How can we help you?",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            SupportItem(
                icon = Icons.Default.QuestionAnswer,
                title = "Live Chat",
                description = "Chat with our support team in real-time"
            )

            SupportItem(
                icon = Icons.Default.Email,
                title = "Email Support",
                description = "Send us an email and we'll reply within 24 hours",
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = "mailto:support@reademstaff.com".toUri()
                        putExtra(Intent.EXTRA_SUBJECT, "Support Request - Read'em Staff")
                    }
                    context.startActivity(intent)
                }
            )

            SupportItem(
                icon = Icons.Default.Language,
                title = "Help Center",
                description = "Browse through our documentation and FAQs"
            )

            Spacer(modifier = Modifier.height(48.dp))
            
            SectionHeader(title = "Common Issues")
            
            FAQItem(question = "How do I cancel my subscription?", answer = "You can cancel your subscription anytime in the 'Manage Plan' section of the settings.")
            FAQItem(question = "Can I read books offline?", answer = "Yes, Pro members can download books to their local repository for offline access.")
            FAQItem(question = "How do I report a bug?", answer = "Please use the 'Email Support' option above to send us technical details.")
        }
    }
}

@Composable
fun SupportItem(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF2C2C2C), shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = AccentSalmon)
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(text = title, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = description, color = TextSecondary, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun FAQItem(question: String, answer: String) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(text = question, fontWeight = FontWeight.SemiBold, color = Color.White, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = answer, color = TextSecondary, fontSize = 13.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun SupportScreenPreview() {
    ProyekAkhirKewirausahaanTheme {
        SupportScreen()
    }
}
