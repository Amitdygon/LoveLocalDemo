package com.example.lovelocaldemo.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.lovelocaldemo.R


@BindingAdapter("setImageUrl")
fun setImageUrl(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("bindSelection")
fun setSelectedView(
    view: ImageView,
    isSelected: Boolean?
) {
    if (isSelected == true) {
        view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.pink))
    } else {
        view.setBackgroundResource(0)
    }
}