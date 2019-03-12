package com.outsmart.os_arturito.Components.ListComponent

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.outsmart.os_arturito.R

abstract class OSAdapter<T>(
        private var isInitialLoading: Boolean = true
) : RecyclerView.Adapter<OSAdapter.OSViewHolder>() {

    val dataList: MutableList<T> = mutableListOf()
    private var placeHolderText = ""
    private var paginationListener: GenericAdapterListener? = null
    private var isPlaceholder = false
    private var hasMoreItems = false
    private val loadMoreLayout = R.layout.component_load_more
    private val loadingLayout = R.layout.component_loading

    init {
        setupInitialLoading()
    }

    private fun setupInitialLoading() {
        if (isInitialLoading) {
            notifyDataSetChanged()
        }
    }

    fun setDataToAdapterAndNotifyChanges(dataList: List<T>) {
        clearDataAndCheckIfListIsEmpty(dataList)
        notifyDataSetChanged()
    }

    fun setHasMoreItems(hasMoreItems: Boolean) {
        this.hasMoreItems = hasMoreItems
    }

    private fun clearDataAndCheckIfListIsEmpty(dataList: List<T>) {
        removeInitialLoading()
        this.dataList.clear()
        checkIfListIsNotEmpty(dataList)
    }

    private fun removeInitialLoading() {
        isInitialLoading = false
    }

    private fun checkIfListIsNotEmpty(dataList: List<T>) {
        if (dataList.isNotEmpty()) {
            setNewDataAndRemovePlaceHolder(dataList)
        } else {
            addPlaceHolder()
        }
    }

    private fun setNewDataAndRemovePlaceHolder(dataList: List<T>) {
        this.dataList.addAll(dataList)
        isPlaceholder = false
    }

    private fun addPlaceHolder() {
        isPlaceholder = true
    }

    fun setPlaceHolderText(placeHolderText: String) {
        this.placeHolderText = placeHolderText
    }

    fun setPaginationListener(paginationListener: GenericAdapterListener) {
        this.paginationListener = paginationListener
    }

    override fun getItemCount(): Int {
        return if (isPlaceholderOrInitialLoadingSet()) {
            OSAdapterConstants.PLACEHOLDER_COUNT
        } else {
            getListSize()
        }
    }

    private fun isPlaceholderOrInitialLoadingSet(): Boolean {
        return isPlaceholder && placeHolderText.isNotBlank() || isInitialLoading
    }

    private fun getListSize(): Int {
        return if (hasMoreItems) {
            dataList.size.plus(1)
        } else {
            dataList.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            isPlaceholderSet() -> OSAdapterConstants.PLACEHOLDER
            isInitialLoading -> OSAdapterConstants.INITIAL_LOADING
            checkForMoreItems(position) -> OSAdapterConstants.PAGE_LOADING
            else -> setViewTypes(position)
        }
    }

    private fun checkForMoreItems(position: Int): Boolean {
        return position == dataList.size
    }

    private fun isPlaceholderSet(): Boolean {
        return isPlaceholder && placeHolderText.isNotBlank()
    }

    abstract fun setViewTypes(position: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OSViewHolder {
        return when (viewType) {
            OSAdapterConstants.PLACEHOLDER -> getPlaceholderViewHolder(parent)
            OSAdapterConstants.PAGE_LOADING -> getLoadMoreViewHolder(parent)
            OSAdapterConstants.INITIAL_LOADING -> getInitialLoadingViewHolder(parent)
            else -> getViewHolder(parent, viewType)
        }
    }

    private fun getInitialLoadingViewHolder(parent: ViewGroup): OSViewHolder {
        return LoadingViewHolder(getInflatedLayout(parent, R.layout.component_loading))
    }

    private fun getLoadMoreViewHolder(parent: ViewGroup): OSViewHolder {
        return PageLoadingViewHolder(getInflatedLayout(parent, loadMoreLayout))
    }

    private fun getInflatedLayout(parent: ViewGroup, layout: Int): View {
        return LayoutInflater.from(parent.context).inflate(layout, parent, false)
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): OSViewHolder

    private fun getPlaceholderViewHolder(parent: ViewGroup): OSViewHolder {
        return PlaceholderViewHolder(
                itemView = PlaceholderView(context = parent.context),
                placeholderText = placeHolderText)
    }

    override fun onBindViewHolder(holder: OSViewHolder, position: Int) {
        holder.bindView(position)
        paginateOnLastBind(position)
    }

    private fun paginateOnLastBind(position: Int) {
        if (position == dataList.lastIndex) {
            paginationListener?.paginate()
        }
    }

    abstract class OSViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindView(position: Int)
    }
}