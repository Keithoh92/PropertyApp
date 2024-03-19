package com.example.hwapp.view.propertyDetails.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hwapp.view.propertyDetails.view.PropertyDetailsScreenMain
import com.example.hwapp.view.propertyDetails.viewmodel.PropertyDetailsViewModel

const val propertyDetailsRoute = "property_details_route"
const val propertyDetailsArgs = "property_details_args"

fun NavController.navigateToPropertyDetailsScreen(
    propertyId: Long,
    navOptions: NavOptions? = null
) {
    this.navigate("$propertyDetailsRoute/${propertyId}", navOptions)
}

fun NavGraphBuilder.propertyDetailsScreen(onBack: () -> Unit) {
    composable(
        route = "$propertyDetailsRoute/{$propertyDetailsArgs}",
        arguments = listOf(navArgument(propertyDetailsArgs) { type = NavType.LongType })
    ) { navBackStackEntry ->
        val viewModel = hiltViewModel<PropertyDetailsViewModel>()
        val propertyId = navBackStackEntry.arguments?.getLong(propertyDetailsArgs)

        PropertyDetailsScreenMain(viewModel = viewModel, propertyId, onBack)
    }
}
