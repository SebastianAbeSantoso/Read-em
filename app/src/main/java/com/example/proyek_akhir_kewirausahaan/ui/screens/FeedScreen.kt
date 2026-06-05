package com.example.proyek_akhir_kewirausahaan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Factory
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyek_akhir_kewirausahaan.viewmodel.ReademUiState
import com.example.proyek_akhir_kewirausahaan.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    uiState: ReademUiState
) {
    val books = uiState.books
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState,
        flingBehavior = flingBehavior
    ) {
        items(books) { book ->
            Box(modifier = Modifier.fillParentMaxSize()) {
                Image(
                    painter = painterResource(R.drawable.mymbg),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = book.title + " background image"
                )

                Column(modifier = Modifier.fillMaxSize()){
                    Spacer(modifier = Modifier.weight(0.7f))

                    Row(Modifier.weight(1f)) {
                        Column(Modifier.weight(1f)) {
                            Image(
                                painter = painterResource(R.drawable.mymbg),
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                                contentDescription = book.title + " background image"
                            )
                        }

                        Column(Modifier.fillMaxHeight().weight(0.3f), verticalArrangement = Arrangement.spacedBy(36.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Column(modifier = Modifier.fillMaxWidth().weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Default.Factory,
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )

                                Text("Hi")
                            }

                            Column(modifier = Modifier.fillMaxWidth().weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Default.Factory,
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )

                                Text("Hi")
                            }

                            Column(modifier = Modifier.fillMaxWidth().weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Default.Factory,
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )

                                Text("Hi")
                            }

                            Column(modifier = Modifier.fillMaxWidth().weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Default.Factory,
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )

                                Text("Hi")
                            }
                        }
                    }
                }
            }
        }
    }
}
