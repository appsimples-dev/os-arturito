package com.outsmart.os_arturito.Components.ListComponent

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

class PlaceholderViewHolder(
        itemView: View,
        private val placeholderText: String) : OSAdapter.OSViewHolder(itemView) {

    override fun bindView(position: Int) {
        (itemView as PlaceholderView).setText(placeholderText)
        setLayoutParametersToMatchParent(itemView)
    }

    private fun setLayoutParametersToMatchParent(itemView: PlaceholderView) {
        val layoutParameter = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        itemView.layoutParams = layoutParameter
    }

}