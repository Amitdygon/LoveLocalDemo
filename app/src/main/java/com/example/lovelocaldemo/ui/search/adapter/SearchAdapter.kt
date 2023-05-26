package com.example.lovelocaldemo.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lovelocaldemo.data.models.response.Product
import com.example.lovelocaldemo.databinding.ItemProductBinding
import com.example.lovelocaldemo.utils.setImageUrl

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private var productList: List<Product?>? = ArrayList()

    inner class ViewHolder(var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(product: Product?) {
            with(binding) {
                setImageUrl(ivProductImage, product?.image)
                tvProductName.text =
                    "${product?.name} ${product?.variants?.get(0)?.units?.toInt() ?: 0} ${
                        product?.variants?.get(0)?.measurement?.abbreviation
                    }"
                tvPrice.text = "${product?.variants?.get(0)?.price}"
                tvDiscount.text = "${product?.variants?.get(0)?.price?.plus(5)}"
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(productList?.get(position))
    }

    fun setData(productList: List<Product?>?) {
        this.productList = productList
        notifyDataSetChanged()
    }

}