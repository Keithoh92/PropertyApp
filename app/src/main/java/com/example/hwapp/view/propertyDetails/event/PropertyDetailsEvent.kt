package com.example.hwapp.view.propertyDetails.event

sealed class PropertyDetailsEvent {
    object OnBack : PropertyDetailsEvent()
    data class OnClickCurrency(val currencyCode: String) : PropertyDetailsEvent()
}
