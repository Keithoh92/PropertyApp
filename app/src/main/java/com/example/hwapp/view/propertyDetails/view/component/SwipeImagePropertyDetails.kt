package com.example.hwapp.view.propertyDetails.view.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeImagePropertyDetails(
    images: List<String>,
    selectedIndex: (Int) -> Unit
) {
    var selectedImageIndex by remember { mutableIntStateOf(0) }

    val pagerState = rememberPagerState(pageCount = { images.size })
    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            key = { images[it] },
            contentPadding = PaddingValues(vertical = 8.dp)
        ) { index ->
            LaunchedEffect(key1 = pagerState, block = {
                snapshotFlow { pagerState.currentPage }.collect { page ->
                    selectedImageIndex = page
                }
            })
            AsyncImage(
                model = "https://${images[index]}",
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .clip(
                        shape = if (selectedImageIndex == 0) {
                            RoundedCornerShape(
                                topStart = 25.dp,
                                topEnd = 0.dp,
                                bottomStart = 25.dp,
                                bottomEnd = 0.dp
                            )
                        } else if (selectedImageIndex == 1) {
                            RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        } else {
                            RoundedCornerShape(0.dp)
                        }
                    )
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
        }
    }
}