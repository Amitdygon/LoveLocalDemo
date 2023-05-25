package com.example.lovelocaldemo.data.models.request

data class CategoryProductReq(
    var page: Int? = null,
    var records: Int? = 10,
    var latitude: Int? = 27,
    var longitude: Int? = 80
)


data class SearchProductReq(
    var searchText: String? = null,
    var page: Int? = null,
    var records: Int? = 1,
    var latitude: Int? = 27,
    var longitude: Int? = 80
)
