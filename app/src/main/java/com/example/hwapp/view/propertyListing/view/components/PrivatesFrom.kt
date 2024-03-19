package com.example.hwapp.view.propertyListing.view.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun PrivatesFrom(price: String) {
    Text(
        text = "Privates from\n\u20AC${price.toFloat().toInt()}",
        color = Color.Black,
        textAlign = TextAlign.Right
    )
}