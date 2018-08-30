package com.outsmart.os_arturito.SimpleCell

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.outsmart.os_arturito.R

class CellListAdapter(private val cells: List<CellModel>,
                      private val context: Context) : Adapter<CellListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cell = cells[position]
        holder?.let {
            it.bindView(cell, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.component_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cells.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(cell: CellModel, position: Int) {
            val cellView: Cell? = Cell.withView(itemView, context)
            cellView?.setModel(cell)
            cellView?.setButtonText("Button")
            if (position == 2) { // testing for text selection
                cellView?.setTitleTextSelectable(true)
                cellView?.setSubtitleSelectable(true)
            }
        }
    }
}