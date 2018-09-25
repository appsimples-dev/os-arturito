package com.outsmart.os_arturito.Components.ListComponent

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by rudieros on 19/08/18.
 */


class GenericAdapter(
        private val layout: Int,
        private var data: List<ListItem>?) : RecyclerView.Adapter<GenericViewHolder>() {


    private var onBottomReachedListener: OnBottonReachedListener? = null


    fun setData(data: List<ListItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return GenericViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        data?.get(position)?.bindView(holder.view)
        if (position == data?.size?.minus(1) ?: 0) {
            onBottomReachedListener?.onBottomReached(position)
        }
    }

    fun setUpOnBottomReachedListener(onBottomReachedListener: OnBottonReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data?.size ?: 0
}