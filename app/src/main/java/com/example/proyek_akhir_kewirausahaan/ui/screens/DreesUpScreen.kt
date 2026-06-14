package com.example.proyek_akhir_kewirausahaan.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyek_akhir_kewirausahaan.R
import kotlin.math.roundToInt

enum class DressGender { MALE, FEMALE }

data class DressLayer(
    val id: String,
    val label: String,
    @DrawableRes val resId: Int,
    val slotZ: Int
)

data class LayerTransform(
    val offsetX: Float = 0f,
    val offsetY: Float = 0f,
    val scale: Float = 1f,
    val visible: Boolean = false
)

private fun maleLayers(): List<DressLayer> = listOf(
    DressLayer("base", "Avatar", R.drawable.ava_male, 0),
    DressLayer("celana", "Celana", R.drawable.celana_base, 1),
    DressLayer("baju", "Baju", R.drawable.baju_base, 2),
    DressLayer("acc_b1_feet", "Sandal Kayu", R.drawable.sendalkayu, 3),
    DressLayer("acc_b3_feet", "Bot Kulit", R.drawable.sepatu, 3),
    DressLayer("acc_b1_hand", "Buku", R.drawable.buku_kuno, 4),
    DressLayer("acc_b3_hand", "Tongkat Sihir", R.drawable.tongkat_sihir, 4),
    DressLayer("acc_b1_eyes", "Penutup Mata", R.drawable.penutup_mata, 5),
    DressLayer("acc_b3_eyes", "Kaca Sihir", R.drawable.kaca_sihir, 5),
    DressLayer("acc_b1_head", "Tudung", R.drawable.tudung, 6),
    DressLayer("acc_b3_head", "Topi Penyihir", R.drawable.topi_penyihir, 6),
    DressLayer("acc_b3_neck", "Tas Sihir", R.drawable.tas_sihir, 4)
)

private fun femaleLayers(): List<DressLayer> = listOf(
    DressLayer("base", "Avatar", R.drawable.ava_female, 0),
    DressLayer("celana", "Celana", R.drawable.celana_base, 1),
    DressLayer("baju", "Baju", R.drawable.baju_base, 2),
    DressLayer("acc_b1_feet", "Sandal Kayu", R.drawable.sendalkayu, 3),
    DressLayer("acc_b3_feet", "Bot Kulit", R.drawable.sepatu, 3),
    DressLayer("acc_b1_hand", "Buku", R.drawable.buku_kuno, 4),
    DressLayer("acc_b3_hand", "Tongkat Sihir", R.drawable.tongkat_sihir, 4),
    DressLayer("acc_b1_eyes", "Penutup Mata", R.drawable.penutup_mata, 5),
    DressLayer("acc_b3_eyes", "Kaca Sihir", R.drawable.kaca_sihir, 5),
    DressLayer("acc_b1_head", "Tudung", R.drawable.tudung, 6),
    DressLayer("acc_b3_head", "Topi Penyihir", R.drawable.topi_penyihir, 6),
    DressLayer("acc_b3_neck", "Tas Sihir", R.drawable.tas_sihir, 4)
)

@Composable
fun DressUpScreen(
    onBack: () -> Unit = {}
) {
    var gender by remember { mutableStateOf(DressGender.MALE) }
    val layers = remember(gender) {
        if (gender == DressGender.MALE) maleLayers() else femaleLayers()
    }

    val transforms = remember(gender) {
        mutableStateMapOf<String, LayerTransform>().apply {
            layers.forEach { put(it.id, LayerTransform(visible = it.id == "base" || it.id == "baju" || it.id == "celana")) }
        }
    }

    var selectedId by remember(gender) { mutableStateOf("base") }

    Scaffold(
        containerColor = Color(0xFF0C0908),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Ruang Ganti", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Row {
                    GenderChip("Cowok", gender == DressGender.MALE) { gender = DressGender.MALE }
                    Spacer(Modifier.width(8.dp))
                    GenderChip("Cewek", gender == DressGender.FEMALE) { gender = DressGender.FEMALE }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
                    .background(Color(0xFF161312)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(322f / 702f)
                ) {
                    layers.sortedBy { it.slotZ }.forEach { layer ->
                        val t = transforms[layer.id] ?: LayerTransform()
                        if (t.visible) {
                            val isSel = layer.id == selectedId && layer.id != "base"
                            Image(
                                painter = painterResource(layer.resId),
                                contentDescription = layer.label,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .offset {
                                        IntOffset(t.offsetX.roundToInt(), t.offsetY.roundToInt())
                                    }
                                    .graphicsLayer(scaleX = t.scale, scaleY = t.scale)
                                    .then(
                                        if (isSel) Modifier.border(1.dp, Color(0xFFE57373)) else Modifier
                                    )
                                    .pointerInput(layer.id, isSel) {
                                        if (isSel) {
                                            detectDragGestures { _, drag ->
                                                val cur = transforms[layer.id] ?: LayerTransform()
                                                transforms[layer.id] = cur.copy(
                                                    offsetX = cur.offsetX + drag.x,
                                                    offsetY = cur.offsetY + drag.y
                                                )
                                            }
                                        }
                                    }
                            )
                        }
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text("Pilih item untuk diatur", color = Color(0xFF8E8E8E), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(layers.filter { it.id != "base" }) { layer ->
                        val t = transforms[layer.id] ?: LayerTransform()
                        ItemChip(
                            label = layer.label,
                            active = t.visible,
                            selected = selectedId == layer.id,
                            onToggleVisible = {
                                transforms[layer.id] = t.copy(visible = !t.visible)
                                if (!t.visible) selectedId = layer.id
                            },
                            onSelect = { selectedId = layer.id }
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                if (selectedId != "base") {
                    val t = transforms[selectedId] ?: LayerTransform()
                    Text("Mengatur: ${layers.first { it.id == selectedId }.label}", color = Color.White, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))

                    Text("Geser X: ${t.offsetX.roundToInt()}", color = Color(0xFF8E8E8E), fontSize = 12.sp)
                    Slider(
                        value = t.offsetX,
                        onValueChange = { transforms[selectedId] = t.copy(offsetX = it) },
                        valueRange = -400f..400f,
                        colors = SliderDefaults.colors(thumbColor = Color(0xFFE57373), activeTrackColor = Color(0xFFE57373))
                    )

                    Text("Geser Y: ${t.offsetY.roundToInt()}", color = Color(0xFF8E8E8E), fontSize = 12.sp)
                    Slider(
                        value = t.offsetY,
                        onValueChange = { transforms[selectedId] = t.copy(offsetY = it) },
                        valueRange = -400f..400f,
                        colors = SliderDefaults.colors(thumbColor = Color(0xFFE57373), activeTrackColor = Color(0xFFE57373))
                    )

                    Text("Skala: ${"%.2f".format(t.scale)}", color = Color(0xFF8E8E8E), fontSize = 12.sp)
                    Slider(
                        value = t.scale,
                        onValueChange = { transforms[selectedId] = t.copy(scale = it) },
                        valueRange = 0.3f..2.5f,
                        colors = SliderDefaults.colors(thumbColor = Color(0xFFE57373), activeTrackColor = Color(0xFFE57373))
                    )

                    Spacer(Modifier.height(8.dp))
                    Surface(color = Color(0xFF161312), shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "x=${t.offsetX.roundToInt()}, y=${t.offsetY.roundToInt()}, scale=${"%.2f".format(t.scale)}",
                            color = Color(0xFFE57373),
                            fontSize = 13.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                } else {
                    Text("Pilih salah satu item di atas untuk mulai mengatur posisinya.", color = Color(0xFF8E8E8E), fontSize = 13.sp)
                }
            }
        }
    }
}

@Composable
private fun GenderChip(label: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        color = if (selected) Color(0xFF3A2C20) else Color(0xFF231F1E),
        shape = RoundedCornerShape(8.dp),
        border = if (selected) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF993C1D)) else null,
        onClick = onClick
    ) {
        Text(
            label,
            color = if (selected) Color(0xFFE57373) else Color(0xFF8E8E8E),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun ItemChip(
    label: String,
    active: Boolean,
    selected: Boolean,
    onToggleVisible: () -> Unit,
    onSelect: () -> Unit
) {
    Surface(
        color = when {
            selected -> Color(0xFF3A2C20)
            active -> Color(0xFF231F1E)
            else -> Color(0xFF161312)
        },
        shape = RoundedCornerShape(8.dp),
        border = if (selected) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE57373)) else null
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                label,
                color = if (active) Color.White else Color(0xFF6E6E6E),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Row {
                MiniButton(if (active) "Lepas" else "Pakai", onToggleVisible)
                if (active) {
                    Spacer(Modifier.width(4.dp))
                    MiniButton("Atur", onSelect)
                }
            }
        }
    }
}

@Composable
private fun MiniButton(label: String, onClick: () -> Unit) {
    Surface(
        color = Color(0xFF2C2C2C),
        shape = RoundedCornerShape(6.dp),
        onClick = onClick
    ) {
        Text(label, color = Color(0xFFE57373), fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
    }
}