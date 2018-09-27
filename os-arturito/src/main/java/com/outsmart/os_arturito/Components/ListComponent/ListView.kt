package com.outsmart.os_arturito.Components.ListComponent

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.paging.LivePagedListBuilder
import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
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
    private var onRequest: (pageKey: String?) -> Unit = {}
    private var layout: Int = -1
    private var isRefreshable: Boolean = false
    private var isPaginatable: Boolean = false

    init {
        LayoutInflater.from(context).inflate(R.layout.component_list_view, this, true)
        this.recyclerView = component_list_view_recycler
        this.refreshLayout = component_list_view_refresh
    }

    fun config(config: ListViewConfig) {
        this.layout = config.layout
        this.onRequest = config.onRequest
        this.recyclerView.layoutManager = LinearLayoutManager(context)
        this.liveDataset = config.liveDataset
        this.isRefreshable = config.isRefreshable
        this.isPaginatable = config.isPaginatable

        configRefresh()
        configPaging()
    }

    private fun configRefresh() {
        if (this.isRefreshable) {
            refreshLayout.setOnRefreshListener({ onRequest(null) })
        }
    }

    private fun configPaging() {
        when (this.isPaginatable) {
            true -> recyclerView.adapter = GenericPagedAdapter(this.layout)
            else -> recyclerView.adapter = GenericAdapter(this.layout)
        }
    }

    private var liveDataset: LiveData<OSList<ListItem>>? = null
        set(value) {
            // we only set onserver if not doing pagination, otherwise
            // the paging library will subscribe to the live data
            if (!this.isPaginatable) {
                value?.observe(context as LifecycleOwner, this.observeListLiveDataset())
            }
        }

    private fun observeListLiveDataset(): Observer<OSList<ListItem>> {
        return Observer {
            it?.let {
                (recyclerView.adapter as? GenericAdapter)?.setData(it.items)
                refreshLayout.isRefreshing = false
            }
        }
    }
}