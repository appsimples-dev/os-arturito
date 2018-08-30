package com.outsmart.os_arturito.Components.SimpleCellComponent

data class CellDatal(
        val id: Int,
        val primaryImage: String? = null,
        val secondaryImage: String? = null,
        val title: String,
        val subTitle: String? = null,
        val secondSubtitle: String? = null,
        val selectable: Boolean = false
)