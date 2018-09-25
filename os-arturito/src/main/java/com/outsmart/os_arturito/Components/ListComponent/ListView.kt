package com.outsmart.os_arturito.Components.ListComponent

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.outsmart.os_arturito.R
import kotlinx.android.synthetic.main.component_list_view.view.*

/**
 * Created by rudieros on 19/08/18.
 */
class ListView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0) : LinearLayout(context, attrs, defStyle) {

    private var recyclerView: RecyclerView
    private var refreshLayout: SwipeRefreshLayout
    private var genericAdapter: GenericAdapter? = null
    private var pageKey: String = ""
    private var onRequest: (pageKey: String?) -> Unit = {}
    private var layout: Int? = null

    private var liveDataset: LiveData<OSList<ListItem>>? = null
        set(value) {
            field = value
            layout?.let {
                recyclerView.adapter = genericAdapter
                field?.observe(context as LifecycleOwner, Observer {
                    it?.let {
                        (recyclerView.adapter as GenericAdapter).setData(it.items)
                        refreshLayout.isRefreshing = false
                        this.pageKey = it.pageKey ?: ""
                    }
                })
            }
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.component_list_view, this, true)
        this.recyclerView = component_list_view_recycler
        this.refreshLayout = component_list_view_refresh
    }
    fun setupPagination() {
        genericAdapter?.setUpOnBottomReachedListener(object : OnBottonReachedListener {
            override fun onBottomReached() {
                onRequest.invoke(pageKey)
            }
        })
    }


    fun config(config: ListViewConfig) {
        this.layout = config.layout
        this.onRequest = config.onRequest
        this.recyclerView.layoutManager = LinearLayoutManager(context)
        this.liveDataset = config.liveDataset
        genericAdapter = GenericAdapter(config.layout, null)
        setupPagination()
        if (config.isRefreshable) {
            refreshLayout.setOnRefreshListener { onRequest(null) }
        }
    }

}
