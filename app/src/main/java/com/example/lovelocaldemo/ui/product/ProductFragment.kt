package com.example.lovelocaldemo.ui.product

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lovelocaldemo.data.models.response.CategoryProductModel
import com.example.lovelocaldemo.databinding.FragmentProductBinding
import com.example.lovelocaldemo.ui.product.adapter.ProductAdapter
import com.example.lovelocaldemo.utils.IntentConstant
import com.example.lovelocaldemo.utils.SimpleItemDecoration
import com.example.lovelocaldemo.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding
    private val productViewModel: ProductViewModel by viewModels()
    private val productList: ArrayList<CategoryProductModel> = ArrayList()

    private var productAdapter: ProductAdapter? = null
    private val categoryModel by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(
                IntentConstant.CATEGORY_MODEL,
                CategoryProductModel::class.java
            )
        } else {
            arguments?.getParcelable(
                IntentConstant.CATEGORY_MODEL
            )
        }
    }

    override fun onResume() {
        super.onResume()
        setListener()
    }

    private fun setListener() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        setAdapter()
        setObservers()
    }

    private fun setUi() {
        with(binding) {
            this.categoryModel = this@ProductFragment.categoryModel
            executePendingBindings()
        }
        productViewModel.hitGetProductApi(categoryModel?.id ?: 0)
    }

    private fun setAdapter() {
        productAdapter = categoryModel?.let { ProductAdapter(it) }
        binding.rvProducts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvProducts.adapter = productAdapter
        binding.rvProducts.addItemDecoration(SimpleItemDecoration(requireContext(), 20, 20, 18, 0))

    }

    private fun setObservers() {
        productViewModel.productListResponse.observe(viewLifecycleOwner) {
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