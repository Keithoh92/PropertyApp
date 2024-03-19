package com.example.hwapp.view.propertyDetails.view.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PropertyDetailsImageCardView(
    images: List<String>
) {

    var selectedImageIndex by remember { mutableIntStateOf(0) }

    Card(
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.White,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {

            SwipeImagePropertyDetails(
                images = images,
                selectedIndex = { selectedImageIndex = it }
            )
        }
}