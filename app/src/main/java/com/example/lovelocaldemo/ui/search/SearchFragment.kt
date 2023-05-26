package com.example.lovelocaldemo.ui.search

import android.app.Activity
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lovelocaldemo.data.models.response.SearchProductModel
import com.example.lovelocaldemo.databinding.FragmentProductBinding
import com.example.lovelocaldemo.listener.LocationInterface
import com.example.lovelocaldemo.ui.product.ProductViewModel
import com.example.lovelocaldemo.ui.search.adapter.SearchAdapter
import com.example.lovelocaldemo.utils.GoogleCurrentLocation
import com.example.lovelocaldemo.utils.IntentConstant
import com.example.lovelocaldemo.utils.SimpleItemDecoration
import com.example.lovelocaldemo.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), LocationInterface {
    private lateinit var binding: FragmentProductBinding
    private val productViewModel: ProductViewModel by viewModels()
    private val productList: ArrayList<SearchProductModel> = ArrayList()
    private var productAdapter: SearchAdapter? = null
    private var googleLocation: GoogleCurrentLocation? = null
    private val searchText by lazy { arguments?.getString(IntentConstant.SEARCH) }


    override fun onResume() {
        super.onResume()
        googleLocation?.checkLocationPermission()
        setListener()
    }

    private fun setListener() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.swipeRefresh.setOnRefreshListener {
            productList.clear()
            productViewModel.hitGetSearchProducts(searchText)
            binding.swipeRefresh.isRefreshing = false
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
        googleLocation = GoogleCurrentLocation(requireActivity(), this)
        setAdapter()
        setObservers()
    }

    private fun setUi(searchProductModel: SearchProductModel?) {
        with(binding) {
            tvTitle.text = searchProductModel?.name
        }
    }

    private fun setAdapter() {
        productAdapter = SearchAdapter()
        binding.rvProducts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvProducts.adapter = productAdapter
        binding.rvProducts.addItemDecoration(SimpleItemDecoration(requireContext(), 20, 20, 18, 0))

    }

    private fun setObservers() {
        productViewModel.searchListResponse.observe(viewLifecycleOwner) {
            it ?: return@observe
            it.data?.let { it1 -> productList.addAll(it1) }
            if (productList.isNullOrEmpty()) {
                binding.tvNoData.visible()
            } else {
                setUi(productList[0])
                productAdapter?.setData(productList = productList[0].products)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == googleLocation?.REQUEST_LOCATION) {
            googleLocation?.checkLocationPermission()
        }
    }

    override fun getLocation(location: Location?) {
        productViewModel.location.value = location
        productViewModel.hitGetSearchProducts(searchText)
    }


}