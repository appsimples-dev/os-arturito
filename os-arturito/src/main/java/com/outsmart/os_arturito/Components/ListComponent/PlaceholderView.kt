package com.outsmart.os_arturito.Components.ListComponent

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.outsmart.os_arturito.R
import kotlinx.android.synthetic.main.component_list_placeholder.view.*

class PlaceholderView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.component_list_placeholder, this)
    }

    fun setText(placeholderText: String) {
        listPlaceholderText.text = placeholderText
    }
}