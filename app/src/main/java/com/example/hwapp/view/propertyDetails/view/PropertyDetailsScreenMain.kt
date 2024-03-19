package com.example.hwapp.view.propertyDetails.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.hwapp.view.propertyDetails.effect.PropertyDetailsEffect
import com.example.hwapp.view.propertyDetails.viewmodel.PropertyDetailsViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PropertyDetailsScreenMain(viewModel: PropertyDetailsViewModel, propertyId: Long?, onBack: () -> Unit) {
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchProperty(propertyId)
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PropertyDetailsEffect.Navigation.Back -> onBack()
            }
        }
    }
    PropertyDetailsScreen(viewModel.uiState, viewModel::onEvent)
}