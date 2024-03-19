package com.example.hwapp.view.propertyListing.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.hwapp.domain.enums.PropertyHighlights
import kotlin.random.Random

@Composable
fun generatePropertyLabels(labels: List<PropertyHighlights>) {
    val colors = listOf(
        Color.Blue,
        Color.Magenta,
        Color.Green,
        Color.Cyan
    )
    val topPaddings = listOf(52, 40, 30)
    for (i in 0..labels.size) {
        return PropertyHighlightLabel(
            label = labels[i].type,
            color = colors[Random.nextInt(0, colors.size)],
            padding = topPaddings[i]
        )
    }
}
@Composable
fun PropertyHighlightLabel(label: String, color: Color, padding: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = padding.dp)
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        color,
                        Color.Transparent
                    ),
                    start = Offset(420f, 90f),
                    end = Offset(490f, 40f)
                )
            )
            .zIndex(3.0f)
    ) {
        Text(
            text = label,
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp, end = 16.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
    }
}