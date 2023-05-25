package com.example.lovelocaldemo.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lovelocaldemo.data.models.response.CategoryProductModel
import com.example.lovelocaldemo.databinding.ItemCategoryBinding

class CategoryAdapter(
    var categoryList: ArrayList<CategoryProductModel>,
    var listener: (position: Int, categoryModel: CategoryProductModel) -> Unit?
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(categoryProductModel: CategoryProductModel) {
            with(binding) {
                categoryModel = categoryProductModel
                categoryProductModel.image?.let { ivCategory.setImageResource(it) }
                executePendingBindings()
            }

            itemView.setOnClickListener {
                listener.invoke(adapterPosition, categoryProductModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(categoryList[position])
    }
}