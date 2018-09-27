package com.outsmart.os_arturito.Components.ListComponent

import android.view.View

/**
 * Created by rudieros on 19/08/18.
 */
abstract class ListItem {
    abstract val id: String
    abstract fun bindView(view: View)
}
