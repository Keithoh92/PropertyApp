package com.example.hwapp.domain.enums

enum class PropertyType {
    HOSTEL, APARTMENT, GUESTHOUSE, HOTEL;

    companion object {
        @JvmStatic
        fun get(name: String): PropertyType? {
            return values().firstOrNull {
                it.name.lowercase() == name.lowercase()
            }
        }
    }
}