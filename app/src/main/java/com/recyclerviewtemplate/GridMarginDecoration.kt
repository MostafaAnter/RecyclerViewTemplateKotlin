package com.recyclerviewtemplate

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GridMarginDecoration(
    context: Context?,
    private val left: Int,
    private val right: Int,
    private val top: Int,
    private val bottom: Int
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect[left, top, right] = bottom
    }
}