package com.example.lovelocaldemo.data.models.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryProductModel(
    var id: Int? = null,
    var name: String? = null,
    var image: Int? = null
) : Parcelable
