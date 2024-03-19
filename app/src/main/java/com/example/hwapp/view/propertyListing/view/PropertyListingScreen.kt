package com.example.hwapp.view.propertyListing.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hwapp.view.propertyListing.event.PropertyListingEvent
import com.example.hwapp.view.propertyListing.state.PropertyListingUIState
import com.example.hwapp.view.propertyListing.view.components.PropertyCardView
import com.example.hwapp.view.propertyListing.view.components.PropertyCardsLoading
import com.example.hwapp.view.propertyListing.view.components.PropertyListings
import com.example.hwapp.view.propertyListing.view.components.TopAppBar
import com.example.hwapp.view.propertyListing.view.components.mockPropertyListing
import com.example.hwapp.view.theme.HWAppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PropertyListingScreen(
    uiState: StateFlow<PropertyListingUIState>,
    onEvent: (PropertyListingEvent) -> Unit,
) {

    val state by uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = "Dublin", onBack = {}) },
        content = { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .consumeWindowInsets(scaffoldPadding)
                    .systemBarsPadding()
                    .padding(horizontal = 12.dp),
            ) {
                if (state.loading) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp),
                    ) {
                        items(4) { index ->
                            PropertyCardsLoading()
                            if (index < 3) Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                } else {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                    ) {
                        Text(
                            text = "Showing ${state.properties.size} Properties.",
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    PropertyListings(
                        isRefreshing = state.isRefreshing,
                        onRefresh = { onEvent(PropertyListingEvent.OnRefresh) }
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp),
                        ) {
                            items(state.properties.size) { index ->
                                val property = state.properties[index]
                                PropertyCardView(
                                    property = property,
                                    onClickProperty = { propertyId ->
                                        onEvent(PropertyListingEvent.OnClickProperty(propertyId))
                                    }
                                )

                                if (index < state.properties.size-1) {
                                    Spacer(modifier = Modifier.height(16.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
private fun PropertyListingScreenPreview() {
    HWAppTheme {
        Surface {
            PropertyListingScreen(
                onEvent = {},
                uiState = MutableStateFlow(mockUIState())
            )
        }
    }
}

fun mockUIState(): PropertyListingUIState {
    return PropertyListingUIState(
        properties = listOf(mockPropertyListing(), mockPropertyListing())
    )
}