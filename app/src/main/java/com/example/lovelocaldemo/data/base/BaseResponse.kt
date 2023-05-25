package com.example.lovelocaldemo.data.base

data class BaseResponse<T>(
    val status: Boolean? = null,
    val data: T? = null,
    val count: Int? = null,
    val prev: String? = null,
    val next: String? = null
)
