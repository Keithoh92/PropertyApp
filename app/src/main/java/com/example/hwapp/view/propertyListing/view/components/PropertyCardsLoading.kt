package com.example.hwapp.view.propertyListing.view.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PropertyCardsLoading() {
    val gradientColors = listOf(
        Color.Gray.copy(alpha = 0.5f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.Gray.copy(alpha = 0.5f)
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = gradientColors,
        start = Offset(0f, 0f), // top left corner
        end = Offset(translateAnim.value, translateAnim.value)
    )

    Card(
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = brush,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(8.dp)
            .height(450.dp),
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {}
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun PropertyCardsLoadingPreview() {
    Surface {
        PropertyCardsLoading()
    }
}