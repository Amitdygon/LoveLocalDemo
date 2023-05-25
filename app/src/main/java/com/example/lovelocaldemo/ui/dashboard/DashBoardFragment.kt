package com.example.lovelocaldemo.ui.dashboard

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lovelocaldemo.R
import com.example.lovelocaldemo.data.models.response.CategoryProductModel
import com.example.lovelocaldemo.databinding.FragmentDashBoardBinding
import com.example.lovelocaldemo.ui.dashboard.adapter.CategoryAdapter
import com.google.android.gms.location.LocationListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardFragment : Fragment(), LocationListener {
    private lateinit var binding: FragmentDashBoardBinding
    private var rootView: View? = null
    private val categoryList: ArrayList<CategoryProductModel> = ArrayList()

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
        setListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCategoryAdapter()
    }

    private fun setCategoryAdapter() {
        binding.rvCategory.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = CategoryAdapter(categoryList) { position, categoryModel ->
            // handle click events here using lambda
            getProducts(categoryModel)
        }
        binding.rvCategory.adapter = adapter
    }

    private fun getProducts(categoryModel: CategoryProductModel) {
        //
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
                searchData()
            }
            false
        })
    }

    private fun searchData() {

    }

    override fun onLocationChanged(p0: Location) {

    }


}