package com.example.lovelocaldemo.data.base

data class ApiError(
    var status: Boolean = false,
    var message: String? = null,
    var result: Any? = null,
    var statusCode: Int? = null,
)