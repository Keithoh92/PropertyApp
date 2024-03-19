package com.example.hwapp.view.propertyListing.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.hwapp.R
import com.example.hwapp.domain.enums.PropertyType
import com.example.hwapp.domain.enums.RatingHierarchy
import com.example.hwapp.domain.model.Property
import com.example.hwapp.view.theme.HWAppTheme

@Composable
fun PropertyCardView(
    property: Property,
    onClickProperty: (propertyId: Long) -> Unit
) {

    var selectedImageIndex by remember { mutableIntStateOf(0) }

    Card(
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.White,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(width = 1.dp, color = Color.LightGray),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable { onClickProperty(property.propertyId) },
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Card(
            colors = CardColors(
                containerColor = Color.White,
                contentColor = Color.White,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                if (property.labelList.isNotEmpty() && selectedImageIndex == 0) {
                    generatePropertyLabels(labels = property.labelList)
                }

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .zIndex(2.0f)
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f),
                            ),
                            start = Offset(9f, 400f),
                            end = Offset(0f, 500f)
                        ),
                        shape = RoundedCornerShape(15.dp)
                    ))
                SwipeImage(
                    images = property.images,
                    selectedIndex = { selectedImageIndex = it }
                )
                Text(
                    text = property.name,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                    ),
                    modifier = Modifier
                        .zIndex(4.0f)
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                )
            }
        }

        Column(modifier = Modifier.padding(8.dp)) {

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                ) {
                    for (i in property.images.indices) {
                        ImageCarouselIndexIndicator(isSelected = i == selectedImageIndex)
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Star",
                    tint = Color(android.graphics.Color.parseColor("#decc40"))
                )
                val ratingOverall = String.format("%.1f", property.ratingOverall / 10.0)
                Text(
                    text = ratingOverall,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.titleLarge
                )
                val ratingHierarchy = RatingHierarchy[property.ratingOverall / 10.0].name
                Text(
                    text = if (ratingHierarchy == RatingHierarchy.NO_RATING.name) {
                        ""
                    } else {
                        ratingHierarchy
                    },
                    color = Color.Gray,
                    textAlign = TextAlign.End
                )
                Text(text = "(${property.numberOfRatings})", color = Color.Gray)
            }
            Column(modifier = Modifier.padding(start = 4.dp)) {
                Text(
                    text = "${property.type.name} - ${property.distance}${property.distanceUnit} from city centre",
                    color = Color.Gray,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Linkups - Amenities icons
                Row {
                    Box() {
                        UserImageIcon(
                            userProfileImage = R.drawable.property1,
                            modifier = Modifier.zIndex(1.0f)
                        )
                        UserImageIcon(
                            userProfileImage = R.drawable.property1,
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .zIndex(2.0f)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .background(
                                Color.Magenta.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(15.dp)
                            )
                    ) {
                        Text(
                            text = "9 Linkups",
                            modifier = Modifier
                                .padding(vertical = 1.dp, horizontal = 8.dp),
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Filled.Wifi,
                        contentDescription = "Wifi",
                        tint = Color.Blue,
                        modifier = Modifier
                            .size(25.dp)
                            .background(
                                Color.Blue.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(4.dp)
                    )
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)) {
                    Spacer(modifier = Modifier.weight(0.6f))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.weight(0.6f)
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
                                    modifier = Modifier.align(Alignment.End).padding(end = 8.dp)
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
                        modifier = Modifier.weight(0.6f)
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
                                    modifier = Modifier.align(Alignment.End).padding(end = 8.dp)
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
        }
    }
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
private fun PropertyCardViewPreview() {
    Surface {
        HWAppTheme {
            PropertyCardView(mockPropertyListing(), onClickProperty = {})
        }
    }
}

fun mockPropertyListing(): Property {
     return Property(
        propertyId = 3200,
        name = "Selina Secret Garden Lisbon",
        ratingOverall = 89.00,
        numberOfRatings = "300",
        type = PropertyType.HOSTEL,
        distance = 1.11,
        distanceUnit = "km",
        lowestPricePerNightValue = "25.00",
        lowestPricePerNightCurrency = "EUR",
        lowestDormPricePerNightValue = "15.00",
        lowestDormPricePerNightCurrency = "EUR",
        lowestPrivatePricePerNightValue = "31.00",
        lowestPrivatePricePerNightCurrency = "EUR",
        labelList = emptyList(),
        images = emptyList(),
         city = "Dublin",
         overview = "Property App"
    )
}