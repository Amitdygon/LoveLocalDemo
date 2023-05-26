package com.example.lovelocaldemo.data.respository

import com.example.lovelocaldemo.data.ApiInterface
import com.example.lovelocaldemo.data.base.BaseResponse
import com.example.lovelocaldemo.data.models.request.SearchProductReq
import com.example.lovelocaldemo.data.models.response.CategoryProductModel
import com.example.lovelocaldemo.data.models.response.SearchProductModel
import retrofit2.Response
import javax.inject.Inject

class HomeRepo @Inject constructor(private val apiInterface: ApiInterface) {
    suspend fun hitGetProductsApi(
        categoryId: Int,
        hashMap: HashMap<String, Any>
    ): Response<BaseResponse<ArrayList<CategoryProductModel>>> {
        return apiInterface.hitGetProductsApi(categoryId, hashMap)
    }

    suspend fun hitGetSearchProductsApi(searchProductReq: SearchProductReq): Response<BaseResponse<ArrayList<SearchProductModel>>> {
        return apiInterface.hitGetSearchProductsApi(searchProductReq)
    }
}