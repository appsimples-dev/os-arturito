package com.outsmart.os_arturito.Components.ListComponent

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

class LoadingViewHolder(itemView: View) : OSAdapter.OSViewHolder(itemView) {

    override fun bindView(position: Int) {
        setLayoutParameterToMatchParent()
    }

    private fun setLayoutParameterToMatchParent() {
        val layoutParameter = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        itemView.layoutParams = layoutParameter
    }
}