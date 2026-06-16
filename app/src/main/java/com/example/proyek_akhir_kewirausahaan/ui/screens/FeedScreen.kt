package com.example.proyek_akhir_kewirausahaan.ui.screens

import android.content.Intent
import java.util.Locale
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Reply
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyek_akhir_kewirausahaan.ui.components.FeedActionItem
import com.example.proyek_akhir_kewirausahaan.viewmodel.ReademUiState
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    uiState: ReademUiState,
    onReadFirstChapter: (String) -> Unit,
    onBookClick: (String) -> Unit,
    onToggleFavorite: (String) -> Unit = {},
    onRemoveBook: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val books = remember(uiState.books, uiState.removedBookIds) {
        uiState.books.filter { it.id !in uiState.removedBookIds }
    }
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState,
        flingBehavior = flingBehavior
    ) {
        items(books, key = { it.id }) { book ->
            SwipeableFeedCard(
                book = book,
                onRemove = { onRemoveBook(book.id) },
                onRead = { onReadFirstChapter(book.id) },
                onBookClick = onBookClick,
                onToggleFavorite = onToggleFavorite
            )
        }
    }
}

@Composable
fun LazyItemScope.SwipeableFeedCard(
    book: com.example.proyek_akhir_kewirausahaan.domain.model.Book,
    onRemove: () -> Unit,
    onRead: () -> Unit,
    onBookClick: (String) -> Unit,
    onToggleFavorite: (String) -> Unit
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val swipeThreshold = screenWidth * 0.4f
    
    val offsetX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillParentMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        scope.launch {
                            if (offsetX.value > swipeThreshold.toPx()) {
                                onRead()
                                offsetX.animateTo(0f, spring(stiffness = Spring.StiffnessLow))
                            } else if (offsetX.value < -swipeThreshold.toPx()) {
                                onRemove()
                            } else {
                                offsetX.animateTo(0f, spring(stiffness = Spring.StiffnessMedium))
                            }
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        scope.launch {
                            offsetX.snapTo(offsetX.value + dragAmount.x)
                        }
                    }
                )
            }
            .graphicsLayer {
                translationX = offsetX.value
                rotationZ = (offsetX.value / screenWidth.toPx()) * 15f
            }
    ) {
        // Swipe Labels Overlay
        if (offsetX.value > 50f) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Read Chapter 1",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                        .padding(16.dp)
                )
            }
        } else if (offsetX.value < -50f) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "Remove",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                        .padding(16.dp)
                )
            }
        }

        // Original Card Content
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(book.coverResId),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = book.title + " cover image"
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.3f),
                                Color.Black.copy(alpha = 0.8f)
                            ),
                            startY = 300f
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 40.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = Color(0xFFE57373),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "MUST READ",
                                color = Color.White,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "${book.readingTimeMin} MIN READ",
                            color = Color.White.copy(alpha = 0.8f),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = book.title.uppercase(),
                        color = Color.White,
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clickable { onBookClick(book.id) }
                    )

                    Text(
                        text = "by ${book.author}",
                        color = Color(0xFFE57373),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = book.synopsis,
                        color = Color.White.copy(alpha = 0.9f),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(0.85f)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = { onRead() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(56.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(Color(0xFFE57373), Color(0xFFD32F2F))
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "READ FIRST CHAPTER",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .width(60.dp)
                        .padding(bottom = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(28.dp)
                ) {
                    FeedActionItem(
                        icon = Icons.Default.Favorite,
                        label = if (book.readersCount >= 1000) "${String.format(Locale.US, "%.1f", book.readersCount / 1000.0)}K" else "${book.readersCount}",
                        onClick = { /* Handle Like */ }
                    )
                    FeedActionItem(
                        icon = Icons.AutoMirrored.Filled.Reply,
                        label = "SHARE",
                        onClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, "Check out this book on Read'em")
                                putExtra(Intent.EXTRA_TEXT, "I'm reading '${book.title}' by ${book.author} on Read'em! \n\n${book.synopsis}")
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share book"))
                        }
                    )
                    FeedActionItem(
                        icon = if (book.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        label = "SAVE",
                        onClick = { onToggleFavorite(book.id) },
                        tint = if (book.isFavorite) Color(0xFFE57373) else Color.White
                    )
                    FeedActionItem(
                        icon = Icons.Default.BarChart,
                        label = "STATS",
                        onClick = { /* Handle Stats */ }
                    )
                }
            }
        }
    }
}
