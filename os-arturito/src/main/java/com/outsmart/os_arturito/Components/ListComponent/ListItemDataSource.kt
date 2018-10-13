package com.outsmart.os_arturito.Components.ListComponent

import android.arch.paging.ItemKeyedDataSource

/**
 * Created by rudieros on 27/09/18.
 */
class ListItemDataSource() : ItemKeyedDataSource<Any, ListItem>() {
    override fun loadBefore(params: LoadParams<Any>, callback: LoadCallback<ListItem>) {

    }

    override fun getKey(item: ListItem): Any {
        return item.id
    }

    override fun loadAfter(params: LoadParams<Any>, callback: LoadCallback<ListItem>) {
        print("Heyo")
    }

    override fun loadInitial(params: LoadInitialParams<Any>, callback: LoadInitialCallback<ListItem>) {
        print("Heysa")
    }
}