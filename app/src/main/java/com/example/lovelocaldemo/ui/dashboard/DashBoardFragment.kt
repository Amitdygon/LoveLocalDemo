package com.example.lovelocaldemo.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lovelocaldemo.R
import com.example.lovelocaldemo.data.models.response.CategoryProductModel
import com.example.lovelocaldemo.databinding.FragmentDashBoardBinding
import com.example.lovelocaldemo.listener.LocationInterface
import com.example.lovelocaldemo.ui.dashboard.adapter.CategoryAdapter
import com.example.lovelocaldemo.utils.GoogleCurrentLocation
import com.example.lovelocaldemo.utils.IntentConstant
import com.example.lovelocaldemo.utils.SimpleItemDecoration
import com.example.lovelocaldemo.utils.getAddress
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DashBoardFragment : Fragment(), LocationInterface {
    private lateinit var binding: FragmentDashBoardBinding
    private var rootView: View? = null
    private val categoryList: ArrayList<CategoryProductModel> = ArrayList()
    private var googleLocation: GoogleCurrentLocation? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (rootView == null) {
            binding = FragmentDashBoardBinding.inflate(inflater, container, false)
            rootView = binding.root
            getCategoryList()
        }
        return rootView

    }

    override fun onResume() {
        super.onResume()
        googleLocation?.checkLocationPermission()
        setListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        googleLocation = GoogleCurrentLocation(requireActivity(), this)
        setCategoryAdapter()
    }

    private fun setCategoryAdapter() {
        binding.rvCategory.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = CategoryAdapter(categoryList) { position, categoryModel ->
            // handle click events here using lambda
            getProducts(categoryModel)
        }
        binding.rvCategory.adapter = adapter
        binding.rvCategory.addItemDecoration(SimpleItemDecoration(requireContext(),8,12,0,0))
    }

    private fun getProducts(categoryModel: CategoryProductModel) {
        //
        val bundle = Bundle()
        bundle.putParcelable(IntentConstant.CATEGORY_MODEL, categoryModel)
        findNavController().navigate(R.id.nav_product_fragment, bundle)
    }

    private fun getCategoryList() {
        categoryList.add((CategoryProductModel(7814, "Fruits & Vegetables", R.drawable.ic_fruits)))
        categoryList.add((CategoryProductModel(7828, "Meat & Seafood", R.drawable.ic_meat)))
        categoryList.add((CategoryProductModel(7827, "Health & Medicine", R.drawable.ic_health)))
        categoryList.add((CategoryProductModel(7818, "Dairy", R.drawable.ic_dairy)))
        categoryList.add((CategoryProductModel(7915, "Chocolates & Snacks", R.drawable.ic_snacks)))
        categoryList.add((CategoryProductModel(7821, "Personal Care", R.drawable.ic_personal_care)))

    }

    private fun setListener() {
        binding.etSearch.setOnEditorActionListener(TextView.OnEditorActionListener { p0, p1, p2 ->
            if (p1 == EditorInfo.IME_ACTION_SEARCH) {
                if (!binding.etSearch.text.toString().trim().isNullOrEmpty()) {
                    searchData(binding.etSearch.text.toString().trim())
                }

            }
            false
        })
    }

    private fun searchData(search: String) {
        val bundle = Bundle()
        bundle.putString(IntentConstant.SEARCH, search)
        findNavController().navigate(R.id.nav_search_fragment, bundle)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == googleLocation?.REQUEST_LOCATION) {
            googleLocation?.checkLocationPermission()
        }
    }

    override fun getLocation(location: Location?) {
        Geocoder(requireContext(), Locale("in")).getAddress(
            latitude = location?.latitude ?: 27.0,
            longitude = location?.longitude ?: 28.0
        ) {
            binding.tvLocation.text =
                "${it?.locality} ${it?.adminArea} ${it?.countryName} ${it?.postalCode}"
        }
    }


}