package com.outsmart.os_arturito.Components.ListComponent

import android.arch.lifecycle.LiveData

/**
 * Created by rudieros on 24/08/18.
 */
class ListViewConfig(
        val liveDataset: LiveData<OSList<ListItem>>,
        val layout: Int,
        val isRefreshable: Boolean = true,
        val onRequest: (pageKey: String?) -> Unit = {},
        val adapterListener: GenericAdapterListener
)