package com.example.proyek_akhir_kewirausahaan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.runtime.SideEffect
import com.example.proyek_akhir_kewirausahaan.ui.theme.ProyekAkhirKewirausahaanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val view = LocalView.current
            SideEffect {
                val window = (view.context as android.app.Activity).window
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            }
            ProyekAkhirKewirausahaanTheme {
                ReademApp()
            }
        }
    }
}