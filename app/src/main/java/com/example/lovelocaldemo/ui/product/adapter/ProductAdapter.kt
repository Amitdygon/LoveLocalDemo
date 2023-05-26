package com.example.lovelocaldemo.ui.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lovelocaldemo.data.models.response.CategoryProductModel
import com.example.lovelocaldemo.databinding.ItemProductBinding

class ProductAdapter(var categoryProductModel: CategoryProductModel) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private var productList: ArrayList<CategoryProductModel> = ArrayList()

    inner class ViewHolder(var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(searchProductModel: CategoryProductModel) {
            with(binding) {
                tvProductName.text = searchProductModel.name
                categoryProductModel.image?.let { ivProductImage.setImageResource(it) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setData(it: ArrayList<CategoryProductModel>) {
        productList = it
        notifyDataSetChanged()
    }

}