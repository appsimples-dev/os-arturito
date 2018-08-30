package com.outsmart.os_arturito.SimpleCell

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.outsmart.os_arturito.R
import kotlinx.android.synthetic.main.activity_cell.*

open class CellsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cell)
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        cells_activity_recyclerview.adapter = CellListAdapter(mockCells(), this)
        val layoutManager = LinearLayoutManager(this)
        cells_activity_recyclerview.layoutManager = layoutManager
    }

    private fun mockCells(): List<CellModel> {
        return listOf(
                CellModel(id = 1, title = "cell clicable", buttonsType =  ButtonsType.Single, buttonListener = this::onButtonTap, cellListener = this::onCellTap),
                CellModel(id = 1, title = "cell clicable", buttonsType =  ButtonsType.Double, buttonListener = this::onButtonTap, cellListener = this::onCellTap)
//                CellModel(1,"Title, cell clickable", null,null, this::onButtonTap, this::onCellTap),
//                CellModel(2,"Title without button", "With subtitle", null, this::onButtonTap , null),
//                CellModel(3,"Title without subtitle, text selectable and cell clickable", null, null,this::onButtonTap, this::onCellTap),
//                CellModel(4,"Title without button", null,null, null, null),
//                CellModel(4,"Title without button", "With subtitle","With second subtitle", null, null)
        )
    }

    private fun onButtonTap(id: Int){
        Toast.makeText(this, "onButtonTap called, id: $id", Toast.LENGTH_LONG).show()
    }

    private fun onCellTap(id: Int) {
        Toast.makeText(this, "onCellTap called, id: $id", Toast.LENGTH_LONG).show()
    }

}