package com.example.hwapp.view.propertyListing.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage

@Composable
fun UserImageIcon(
    userProfileImage: Int,
    userProfileNationality: Int? = null,
    modifier: Modifier,
) {
    Box(modifier = modifier) {
        RoundImage(
            image = userProfileImage,
            description = "user profile image",
            modifier = Modifier
                .size(25.dp)
                .zIndex(1.0f)
        )
        AnimatedVisibility(visible = userProfileNationality != null) {
            RoundImage(
                image = userProfileNationality!!,
                description = "user nationality icon",
                modifier = Modifier
                    .size(12.dp)
                    .zIndex(2.0f)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
fun RoundImage(
    image: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = image,
        contentDescription = description,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}