package com.example.hwapp.view.propertyDetails.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hwapp.R
import com.example.hwapp.view.propertyDetails.event.PropertyDetailsEvent
import com.example.hwapp.view.propertyDetails.state.PropertyDetailsUIState
import com.example.hwapp.view.propertyDetails.view.component.ExpandableText
import com.example.hwapp.view.propertyDetails.view.component.PropertyDetailsImageCardView
import com.example.hwapp.view.propertyListing.view.components.DormsFrom
import com.example.hwapp.view.propertyListing.view.components.PrivatesFrom
import com.example.hwapp.view.propertyListing.view.components.TopAppBar
import com.example.hwapp.view.propertyListing.view.components.mockPropertyListing
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jsoup.Jsoup

@Composable
fun PropertyDetailsScreen(
    uiState: StateFlow<PropertyDetailsUIState>,
    onEvent: (PropertyDetailsEvent) -> Unit
) {
    val state by uiState.collectAsState()
    val property = state.property
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = { TopAppBar(title = property.name, onBack = { onEvent(PropertyDetailsEvent.OnBack) }) },
        bottomBar = {  },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        content = { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .consumeWindowInsets(scaffoldPadding)
                    .systemBarsPadding()
                    .padding(horizontal = 12.dp)
                    .verticalScroll(scrollState)
                    .consumeWindowInsets(WindowInsets(0, 0, 0, 0)),
            ) {
                PropertyDetailsImageCardView(images = state.property.images)

                Text(
                    text = property.type.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 8.dp, top = 16.dp)
                )
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Row {
                        Text(
                            text = property.name,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Star,
                                contentDescription = "Star",
                                modifier = Modifier.size(25.dp),
                                tint = Color(android.graphics.Color.parseColor("#decc40"))
                            )
                            val ratingOverall = String.format("%.1f", property.ratingOverall / 10.0)
                            Text(
                                text = ratingOverall,
                                color = Color.Black,
                                fontWeight = FontWeight.ExtraBold,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }

                    Row {
                        Row {
                            Icon(
                                imageVector = Icons.Filled.LocationOn,
                                contentDescription = "Location",
                                tint = Color.Gray
                            )
                            Text(
                                text = property.city,
                                style = MaterialTheme.typography.titleSmall,
                                color = Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "${property.numberOfRatings} reviews", color = Color.Gray)
                    }
                }
                var expanded by remember { mutableStateOf(false) }
                val radioOptions = listOf("EUR", "GBP", "USD")

                Row {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)) {

                        Row(
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { expanded = !expanded }
                                .padding(start = 8.dp)
                        ) {
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier.background(Color.Gray)
                            ) {
                                radioOptions.forEach {
                                    DropdownMenuItem(
                                        onClick = {
                                            onEvent(PropertyDetailsEvent.OnClickCurrency(it))
                                            expanded = !expanded
                                        },
                                        text = { Text(text = it, color = Color.Black) },
                                        colors = MenuItemColors(
                                            textColor = Color.Black,
                                            leadingIconColor = Color.Black,
                                            trailingIconColor = Color.Black,
                                            disabledLeadingIconColor = Color.Black,
                                            disabledTextColor = Color.Black,
                                            disabledTrailingIconColor = Color.Black
                                        )
                                    )
                                }
                            }
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            when {
                                property.isDiscountedPrivate() -> {
                                    Row(
                                        horizontalArrangement = Arrangement.End,
                                        modifier = Modifier
                                            .background(color = colorResource(id = R.color.pink_discount))
                                            .align(Alignment.End)
                                            .padding(4.dp)
                                    ) {
                                        Text(
                                            text = "-${property.formatDiscount(property.lowestAveragePrivatePricePerNightDiscount!!)}%",
                                            color = Color.White
                                        )
                                    }
                                    PrivatesFrom(price = property.lowestAveragePrivatePricePerNightValue!!)
                                    Text(
                                        text = "\u20AC${(property.lowestAveragePrivatePricePerNightOriginal?.toFloat()?.toInt()
                                            ?: property.calculatePrivateDiscount().toFloat().toInt())}",
                                        color = Color.Black,
                                        textDecoration = TextDecoration.LineThrough,
                                        modifier = Modifier
                                            .align(Alignment.End)
                                            .padding(end = 8.dp)
                                    )
                                }
                                else -> {
                                    val lowestPrivatePrice = property.lowestPrivatePricePerNightValue
                                    if (lowestPrivatePrice != null) {
                                        PrivatesFrom(price = property.lowestPrivatePricePerNightValue)
                                    } else {
                                        Text(text = "No Private\nAvailability", color = Color.Black)
                                    }
                                }
                            }
                        }
                        VerticalDivider(modifier = Modifier
                            .height(40.dp)
                            .padding(horizontal = 16.dp))

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            when {
                                property.isDiscountedDorm() -> {
                                    Row(
                                        horizontalArrangement = Arrangement.End,
                                        modifier = Modifier
                                            .background(color = colorResource(id = R.color.pink_discount))
                                            .align(Alignment.End)
                                            .padding(4.dp)
                                    ) {
                                        Text(
                                            text = "-${property.formatDiscount(property.lowestAverageDormPricePerNightDiscount!!)}%",
                                            color = Color.White
                                        )
                                    }
                                    DormsFrom(price = property.lowestAverageDormPricePerNightValue!!)
                                    Text(
                                        text = "\u20AC${(property.lowestAverageDormPricePerNightOriginal?.toFloat()?.toInt()
                                            ?: property.calculateDormDiscount().toFloat().toInt())}",
                                        color = Color.Black,
                                        textDecoration = TextDecoration.LineThrough,
                                        modifier = Modifier
                                            .align(Alignment.End)
                                            .padding(end = 8.dp)
                                    )
                                }
                                else -> {
                                    val lowestDormPricePerNight = property.lowestDormPricePerNightValue
                                    val lowestPricePerNight = property.lowestPricePerNightValue
                                    if (lowestDormPricePerNight != null) {
                                        DormsFrom(price = property.lowestDormPricePerNightValue)
                                    } else if (lowestPricePerNight != null) {
                                        DormsFrom(price = property.lowestPricePerNightValue)
                                    } else {
                                        Text(text = "No Dorm\nAvailability", color = Color.Black)
                                    }
                                }
                            }
                        }
                    }
                }

                val decodedHtmlString = decodeHtml(property.overview)

                Column(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "Overview",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium
                    )
                    ExpandableText(text = decodedHtmlString)
                }
            }
        }
    )
    BackHandler(onBack = { onEvent(PropertyDetailsEvent.OnBack) })
}

fun decodeHtml(input: String): String {
    return Jsoup.parse(input).text()
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
private fun PropertyDetailsScreenPreview() {
    Surface {
        PropertyDetailsScreen(uiState = MutableStateFlow(mockUIState()), onEvent = {})
    }
}

fun mockUIState(): PropertyDetailsUIState {
    return PropertyDetailsUIState(
        property = mockPropertyListing()
    )
}