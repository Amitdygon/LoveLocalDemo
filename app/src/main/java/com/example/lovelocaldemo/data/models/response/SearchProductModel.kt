package com.example.lovelocaldemo.data.models.response

data class SearchProductModel(
    val id: Int? = null,
    val is_active: Boolean? = null,
    val location: Location? = null,
    val name: String? = null,
    val products: List<Product?>? = null
)

data class Measurement(
    val abbreviation: String? = null,
    val name: String? = null
)

data class Location(
    val lat: Double? = null,
    val lon: Double? = null
)

data class Brand(
    val image: String? = null,
    val name: String? = null
)

