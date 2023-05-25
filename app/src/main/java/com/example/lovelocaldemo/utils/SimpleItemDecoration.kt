package com.example.lovelocaldemo.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SimpleItemDecoration(
    context: Context,
    private val left: Int = 10,
    private var right: Int = 10,
    private val top: Int = 10,
    private val bottom: Int = 10
) : RecyclerView.ItemDecoration() {

    private val leftInDp = AppUtils.dpToPx(context, left)
    private val rightInDp = AppUtils.dpToPx(context, right)
    private val topInDp = AppUtils.dpToPx(context, top)
    private val bottomInDp = AppUtils.dpToPx(context, bottom)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        outRect.left = leftInDp
        outRect.right = rightInDp
        outRect.bottom = bottomInDp
        outRect.top = topInDp

    }
}