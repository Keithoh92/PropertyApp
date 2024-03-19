package com.example.hwapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.hwapp.view.propertyDetails.navigation.navigateToPropertyDetailsScreen
import com.example.hwapp.view.propertyDetails.navigation.propertyDetailsScreen
import com.example.hwapp.view.propertyListing.navigation.propertyListingRoute
import com.example.hwapp.view.propertyListing.navigation.propertyListingScreen
import com.example.hwapp.view.theme.HWAppTheme
import com.example.hwapp.view.propertyListing.view.components.PropertyCardView
import com.example.hwapp.view.propertyListing.view.components.mockPropertyListing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier, color = Color.White) {
                navController = rememberNavController()
                NavHost(navController = navController, startDestination = propertyListingRoute) {
                    propertyListingScreen(
                        onClickProperty = { propertyId ->
                            navController.navigateToPropertyDetailsScreen(propertyId)
                        }
                    )
                    propertyDetailsScreen(
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HWAppTheme {
        PropertyCardView(mockPropertyListing(), onClickProperty = {})
    }
}