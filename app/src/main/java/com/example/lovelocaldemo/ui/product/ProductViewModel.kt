package com.example.lovelocaldemo.ui.product

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lovelocaldemo.data.ApiParams
import com.example.lovelocaldemo.data.base.BaseResponse
import com.example.lovelocaldemo.data.models.response.CategoryProductModel
import com.example.lovelocaldemo.data.models.response.SearchProductModel
import com.example.lovelocaldemo.data.respository.HomeRepo
import com.example.lovelocaldemo.data.viewModel.CoroutinesBase
import com.example.lovelocaldemo.utils.AppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {

    val location = MutableLiveData<Location?>()
    private val _productList = MutableLiveData<BaseResponse<ArrayList<CategoryProductModel>>?>()
    val productListResponse: LiveData<BaseResponse<ArrayList<CategoryProductModel>>?> = _productList

    private val _searchList = MutableLiveData<BaseResponse<ArrayList<SearchProductModel>>?>()
    val searchListResponse: LiveData<BaseResponse<ArrayList<SearchProductModel>>?> = _searchList

    fun hitGetProductApi(categoryId: Int) {
        if (!AppUtils.isInternetAvailable()) return
        val hashMap = hashMapOf<String, Any>(
            ApiParams.page to 1,
            ApiParams.records to 10,
            ApiParams.latitude to 27,
            ApiParams.longitude to 80
        )
        CoroutinesBase.main {
            val response = homeRepo.hitGetProductsApi(categoryId, hashMap)
            if (response.isSuccessful) {
                _productList.value = response.body()
            }
        }
    }

    fun hitGetSearchProducts(search: String?) {
        if (!AppUtils.isInternetAvailable()) return
        val hashMap = hashMapOf<String, Any?>(
            ApiParams.searchText to search,
            ApiParams.page to 1,
            ApiParams.records to 1,
            ApiParams.latitude to 27,
            ApiParams.longitude to 80
            /*ApiParams.latitude to location.value?.latitude,
            ApiParams.longitude to location.value?.longitude*/
        )
        CoroutinesBase.main {
            val response = homeRepo.hitGetSearchProductsApi(hashMap)
            if (response.isSuccessful) {
                _searchList.value = response.body()
            }
        }
    }


}