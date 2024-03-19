package com.example.hwapp.view.propertyListing.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.hwapp.view.propertyListing.view.PropertyListingScreenMain
import com.example.hwapp.view.propertyListing.viewmodel.PropertyListingViewModel

const val propertyListingRoute = "property_listing_route"

fun NavGraphBuilder.propertyListingScreen(onClickProperty: (propertyId: Long) -> Unit) {
    composable(route = propertyListingRoute) {
        val viewModel = hiltViewModel<PropertyListingViewModel>()
        PropertyListingScreenMain(viewModel = viewModel, onClickProperty)
    }
}