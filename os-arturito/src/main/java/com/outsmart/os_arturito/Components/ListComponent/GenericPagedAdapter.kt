package com.outsmart.os_arturito.Components.ListComponent

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by rudieros on 19/08/18.
 */


class GenericPagedAdapter(
        private val layout: Int
) : PagedListAdapter<ListItem, GenericViewHolder>(DIFF_CALLBACK) {

    private var data: List<ListItem>? = null
    var listener: GenericAdapterListener? = null

    public fun setData(data: List<ListItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    public fun setAdapterListener(listener: GenericAdapterListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(layout, parent, false)
        return GenericViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        getItem(position)?.bindView(holder.view)
    }

    override fun getItemCount() = data?.size ?: 0

    companion object {
        private val DIFF_CALLBACK = object :
                DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldListItem: ListItem,
                                         newListItem: ListItem): Boolean =
                    oldListItem.id == newListItem.id

            override fun areContentsTheSame(oldListItem: ListItem,
                                            newListItem: ListItem): Boolean =
                    oldListItem == newListItem
        }
    }
}