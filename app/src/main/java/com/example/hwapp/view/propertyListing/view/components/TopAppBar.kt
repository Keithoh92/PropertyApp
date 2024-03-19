package com.example.hwapp.view.propertyListing.view.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hwapp.view.theme.HWAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String, onBack: () -> Unit) {
    androidx.compose.material3.TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.Black,
                modifier = Modifier.padding(start = 4.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = Color.Black,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    )
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun TopAppBarPreview() {
    HWAppTheme {
        TopAppBar(title = "Dublin", onBack = {})
    }
}