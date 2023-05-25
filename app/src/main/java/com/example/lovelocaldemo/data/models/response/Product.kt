package com.example.lovelocaldemo.data.models.response

data class Product(
    val brand: Brand? = null,
    val description: String? = null,
    val id: Int? = null,
    val image: String? = null,
    val is_non_vegetarian: Boolean? = null,
    val name: String? = null,
    val requires_prescription: Boolean? = null,
    val variants: List<Variant?>? = null
)

data class Variant(
    val description: String? = null,
    val id: Int? = null,
    val image: String? = null,
    val measurement: Measurement? = null,
    val name: String? = null,
    val price: Double? = null,
    val units: Double? = null
)