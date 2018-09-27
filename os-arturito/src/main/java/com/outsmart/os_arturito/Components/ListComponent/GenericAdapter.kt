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
        private val layout: Int
        ) : RecyclerView.Adapter<GenericViewHolder>() {

    private var data: List<ListItem>? = null
    var listener: GenericAdapterListener? = null

    public fun setData(data: List<ListItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    public fun setAdapterListener(listener: GenericAdapterListener?) {
        this.listener = listener
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(layout, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return GenericViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        data?.get(position)?.bindView(holder.view)
        Log.d("Generic Adapter:", "Bind" )
        if (data?.size?.minus(1) == position) {
            this.listener?.paginate()
            Log.d("Generic Adapter:", "Last element")
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data?.size ?: 0
}