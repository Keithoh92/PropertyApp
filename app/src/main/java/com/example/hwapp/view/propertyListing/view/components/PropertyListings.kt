package com.example.hwapp.view.propertyListing.view.components

import androidx.compose.runtime.Composable
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun PropertyListings(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {

    val pullState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    SwipeRefresh(
        state = pullState,
        swipeEnabled = true,
        onRefresh = onRefresh
    ) {
        content()
    }
}