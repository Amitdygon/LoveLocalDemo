package com.example.lovelocaldemo.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lovelocaldemo.data.ApiParams
import com.example.lovelocaldemo.data.base.BaseResponse
import com.example.lovelocaldemo.data.models.response.CategoryProductModel
import com.example.lovelocaldemo.data.respository.HomeRepo
import com.example.lovelocaldemo.data.viewModel.CoroutinesBase
import com.example.lovelocaldemo.utils.AppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {

    private val _productList = MutableLiveData<BaseResponse<ArrayList<CategoryProductModel>>?>()
    val productListResponse: LiveData<BaseResponse<ArrayList<CategoryProductModel>>?> = _productList

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


}