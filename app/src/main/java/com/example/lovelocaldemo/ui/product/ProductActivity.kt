package com.example.lovelocaldemo.ui.product

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lovelocaldemo.R
import com.example.lovelocaldemo.data.models.response.CategoryProductModel
import com.example.lovelocaldemo.databinding.FragmentProductBinding
import com.example.lovelocaldemo.ui.product.adapter.ProductAdapter
import com.example.lovelocaldemo.utils.IntentConstant
import com.example.lovelocaldemo.utils.SimpleItemDecoration
import com.example.lovelocaldemo.utils.visible
import com.google.android.gms.common.util.DataUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductActivity : AppCompatActivity() {
    private lateinit var binding: FragmentProductBinding
    private val productViewModel: ProductViewModel by viewModels()
    private val productList: ArrayList<CategoryProductModel> = ArrayList()

    private var productAdapter: ProductAdapter? = null
    private val categoryModel by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(
                IntentConstant.CATEGORY_MODEL,
                CategoryProductModel::class.java
            )
        } else {
            intent?.getParcelableExtra(
                IntentConstant.CATEGORY_MODEL
            )
        }
    }

    override fun onResume() {
        super.onResume()
        setListener()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_product)
        setUi()
        setAdapter()
        setObservers()
    }

    private fun setListener() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.swipeRefresh.setOnRefreshListener {
            productList.clear()
            productViewModel.hitGetProductApi(categoryModel?.id ?: 0)
            binding.swipeRefresh.isRefreshing = false
        }
    }


    private fun setUi() {
        with(binding) {
            this.categoryModel = this@ProductActivity.categoryModel
            executePendingBindings()
        }
        productViewModel.hitGetProductApi(categoryModel?.id ?: 0)
    }

    private fun setAdapter() {
        productAdapter = categoryModel?.let { ProductAdapter(it) }
        binding.rvProducts.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvProducts.adapter = productAdapter
        binding.rvProducts.addItemDecoration(SimpleItemDecoration(this, 20, 20, 18, 0))

    }

    private fun setObservers() {
        productViewModel.productListResponse.observe(this) {
            it ?: return@observe
            it.data?.let { it1 -> productList.addAll(it1) }
            if (productList.isNullOrEmpty()) {
                // show not data found
                binding.tvNoData.visible()
            }
            productAdapter?.setData(productList)

        }
    }


}