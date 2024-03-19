package com.example.hwapp.view.propertyListing.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.hwapp.view.propertyListing.effect.PropertyListingEffect
import com.example.hwapp.view.propertyListing.viewmodel.PropertyListingViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PropertyListingScreenMain(
    viewModel: PropertyListingViewModel,
    onClickProperty: (propertyId: Long) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.initialiseProperties()
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PropertyListingEffect.Navigation.PropertyDetailsScreen -> {
                    onClickProperty(effect.propertyId)
                }
            }
        }
    }
    PropertyListingScreen(
        uiState = viewModel.uiState,
        onEvent = viewModel::onEvent
    )
}