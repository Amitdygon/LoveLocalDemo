package com.example.lovelocaldemo.data

import com.example.lovelocaldemo.data.base.BaseResponse
import com.example.lovelocaldemo.data.models.request.CategoryProductReq
import com.example.lovelocaldemo.data.models.request.SearchProductReq
import com.example.lovelocaldemo.data.models.response.CategoryProductModel
import com.example.lovelocaldemo.data.models.response.SearchProductModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET(ApiEndPoints.GET_PRODUCTS)
    suspend fun hitGetProductsApi(
        @Path(ApiParams.CATEGORY_ID) categoryId: Int,
        @Body categoryProductModel: CategoryProductReq
    ): Response<BaseResponse<ArrayList<CategoryProductModel>>>

    @GET(ApiEndPoints.SEARCH_PRODUCTS)
    suspend fun hitGetSearchProductsApi(
        @Body searchProductReq: SearchProductReq
    ): Response<BaseResponse<ArrayList<SearchProductModel>>>


}