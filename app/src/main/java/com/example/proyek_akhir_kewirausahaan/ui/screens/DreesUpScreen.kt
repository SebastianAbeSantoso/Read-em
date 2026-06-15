package com.example.proyek_akhir_kewirausahaan.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
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
import com.example.proyek_akhir_kewirausahaan.model.data.AvatarLook
import com.example.proyek_akhir_kewirausahaan.model.data.SavedLayer
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
    DressLayer("celana", "Pants", R.drawable.celana_base, 1),
    DressLayer("baju", "Shirt", R.drawable.baju_base, 2),
    DressLayer("acc_b1_feet", "Wooden Sandals", R.drawable.sendalkayu, 3),
    DressLayer("acc_b3_feet", "Leather Boots", R.drawable.sepatu, 3),
    DressLayer("acc_b1_hand", "Book", R.drawable.buku_kuno, 4),
    DressLayer("acc_b3_hand", "Magic Staff", R.drawable.tongkat_sihir, 4),
    DressLayer("acc_b1_eyes", "Blindfold", R.drawable.penutup_mata, 5),
    DressLayer("acc_b3_eyes", "Magic Glasses", R.drawable.kaca_sihir, 5),
    DressLayer("acc_b1_head", "Hood", R.drawable.tudung, 6),
    DressLayer("acc_b3_head", "Wizard Hat", R.drawable.topi_penyihir, 6),
    DressLayer("acc_b1_top", "Robe", R.drawable.jubah, 2),
    DressLayer("acc_b3_neck", "Magic Pouch", R.drawable.tas_sihir, 4)
)

private fun femaleLayers(): List<DressLayer> = listOf(
    DressLayer("base", "Avatar", R.drawable.ava_male, 0),
    DressLayer("celana", "Pants", R.drawable.celana_base, 1),
    DressLayer("baju", "Shirt", R.drawable.baju_base, 2),
    DressLayer("acc_b1_feet", "Wooden Sandals", R.drawable.sendalkayu, 3),
    DressLayer("acc_b3_feet", "Leather Boots", R.drawable.sepatu, 3),
    DressLayer("acc_b1_hand", "Book", R.drawable.buku_kuno, 4),
    DressLayer("acc_b3_hand", "Magic Staff", R.drawable.tongkat_sihir, 4),
    DressLayer("acc_b1_eyes", "Blindfold", R.drawable.penutup_mata, 5),
    DressLayer("acc_b3_eyes", "Magic Glasses", R.drawable.kaca_sihir, 5),
    DressLayer("acc_b1_head", "Hood", R.drawable.tudung, 6),
    DressLayer("acc_b3_head", "Wizard Hat", R.drawable.topi_penyihir, 6),
    DressLayer("acc_b1_top", "Robe", R.drawable.jubah, 2),
    DressLayer("acc_b3_neck", "Magic Pouch", R.drawable.tas_sihir, 4)
)

fun layersFor(gender: DressGender): List<DressLayer> =
    if (gender == DressGender.MALE) maleLayers() else femaleLayers()

@Composable
fun DressUpScreen(
    savedLook: AvatarLook? = null,
    onSave: (AvatarLook) -> Unit = {},
    onBack: () -> Unit = {}
) {
    var gender by remember {
        mutableStateOf(
            if (savedLook?.gender == "FEMALE") DressGender.FEMALE else DressGender.MALE
        )
    }
    val layers = remember(gender) { layersFor(gender) }

    val transforms = remember(gender) {
        mutableStateMapOf<String, LayerTransform>().apply {
            layers.forEach { layer ->
                val saved = savedLook
                    ?.takeIf { (it.gender == "FEMALE") == (gender == DressGender.FEMALE) }
                    ?.layers?.firstOrNull { it.id == layer.id }
                if (saved != null) {
                    put(layer.id, LayerTransform(saved.offsetX, saved.offsetY, saved.scale, saved.visible))
                } else {
                    put(layer.id, LayerTransform(visible = layer.id == "base" || layer.id == "baju" || layer.id == "celana"))
                }
            }
        }
    }

    var selectedId by remember(gender) { mutableStateOf("base") }
    var savedMsg by remember { mutableStateOf(false) }

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
                Text("Dressing Room", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Row {
                    GenderChip("Male", gender == DressGender.MALE) { gender = DressGender.MALE }
                    Spacer(Modifier.width(8.dp))
                    GenderChip("Female", gender == DressGender.FEMALE) { gender = DressGender.FEMALE }
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
                                    .then(
                                        if (isSel) {
                                            Modifier.pointerInput(layer.id) {
                                                detectTransformGestures { _, pan, zoom, _ ->
                                                    val cur = transforms[layer.id] ?: LayerTransform()
                                                    transforms[layer.id] = cur.copy(
                                                        offsetX = cur.offsetX + pan.x,
                                                        offsetY = cur.offsetY + pan.y,
                                                        scale = (cur.scale * zoom).coerceIn(0.3f, 3f)
                                                    )
                                                    savedMsg = false
                                                }
                                            }
                                        } else Modifier))
                        }
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Button(
                    onClick = {
                        val saved = layers.map { layer ->
                            val t = transforms[layer.id] ?: LayerTransform()
                            SavedLayer(layer.id, t.offsetX, t.offsetY, t.scale, t.visible)
                        }
                        onSave(
                            AvatarLook(
                                gender = if (gender == DressGender.FEMALE) "FEMALE" else "MALE",
                                layers = saved
                            )
                        )
                        savedMsg = true
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE57373)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Save Avatar", color = Color.White, fontWeight = FontWeight.Bold)
                }

                if (savedMsg) {
                    Spacer(Modifier.height(8.dp))
                    Text("Avatar saved!", color = Color(0xFF1D9E75), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(Modifier.height(20.dp))

                Text("Select an item to adjust", color = Color(0xFF8E8E8E), fontSize = 12.sp, fontWeight = FontWeight.Bold)
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
                                savedMsg = false
                            },
                            onSelect = { selectedId = layer.id }
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

                if (selectedId != "base") {
                    Text(
                        "Adjusting: ${layers.first { it.id == selectedId }.label}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Drag with one finger to move, pinch with two fingers to resize.",
                        color = Color(0xFF8E8E8E),
                        fontSize = 13.sp
                    )
                } else {
                    Text(
                        "Select an item above, then tap \"Adjust\" to move and resize it directly on the avatar.",
                        color = Color(0xFF8E8E8E),
                        fontSize = 13.sp
                    )
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
                MiniButton(if (active) "Remove" else "Wear", onToggleVisible)
                if (active) {
                    Spacer(Modifier.width(4.dp))
                    MiniButton("Adjust", onSelect)
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

@Composable
fun AvatarLookPreview(
    look: AvatarLook?,
    modifier: Modifier = Modifier
) {
    if (look == null) return
    val gender = if (look.gender == "FEMALE") DressGender.FEMALE else DressGender.MALE
    val layers = layersFor(gender)
    val density = androidx.compose.ui.platform.LocalDensity.current

    androidx.compose.foundation.layout.BoxWithConstraints(modifier = modifier) {
        val boxHeightPx = with(density) { this@BoxWithConstraints.maxHeight.toPx() }
        val designHeightPx = with(density) { 420.dp.toPx() }
        val ratio = if (designHeightPx > 0f) boxHeightPx / designHeightPx else 1f

        layers.sortedBy { it.slotZ }.forEach { layer ->
            val saved = look.layers.firstOrNull { it.id == layer.id }
            val visible = saved?.visible ?: (layer.id == "base" || layer.id == "baju" || layer.id == "celana")
            if (visible) {
                Image(
                    painter = painterResource(layer.resId),
                    contentDescription = layer.label,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .matchParentSize()
                        .offset {
                            IntOffset(
                                ((saved?.offsetX ?: 0f) * ratio).roundToInt(),
                                ((saved?.offsetY ?: 0f) * ratio).roundToInt()
                            )
                        }
                        .graphicsLayer(
                            scaleX = saved?.scale ?: 1f,
                            scaleY = saved?.scale ?: 1f
                        )
                )
            }
        }
    }
}