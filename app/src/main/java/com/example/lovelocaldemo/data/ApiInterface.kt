package com.example.lovelocaldemo.data

import com.example.lovelocaldemo.data.base.BaseResponse
import com.example.lovelocaldemo.data.models.response.CategoryProductModel
import com.example.lovelocaldemo.data.models.response.SearchProductModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET(ApiEndPoints.GET_PRODUCTS)
    suspend fun hitGetProductsApi(
        @Path(ApiParams.category_id) categoryId: Int,
        @QueryMap hashMap: HashMap<String, Any>
    ): Response<BaseResponse<ArrayList<CategoryProductModel>>>

    @GET(ApiEndPoints.SEARCH_PRODUCTS)
    suspend fun hitGetSearchProductsApi(
        @QueryMap hashMap: HashMap<String, Any?>
    ): Response<BaseResponse<ArrayList<SearchProductModel>>>


}