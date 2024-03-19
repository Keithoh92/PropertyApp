package com.example.hwapp.view.propertyDetails.effect

sealed class PropertyDetailsEffect {
    sealed class Navigation : PropertyDetailsEffect() {
        object Back : Navigation()
    }
}