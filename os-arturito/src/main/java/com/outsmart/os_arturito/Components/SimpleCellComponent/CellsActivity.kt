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
                CellModel(id = 1,
                        title = "cell clickable",
                        subTitle = "My cell My cell My cell",
                        leftImage = "j",
                        buttonsType =  ButtonsType.Single,
                        buttonListener = this::onButtonTap,
                        cellListener = this::onCellTap,
                        leftButtonListener = null,
                        rightButtonListener = null,
                        leftImageListener = this::onLeftImageTap),

                CellModel(id = 2,
                        title = "cell clickable",
                        buttonsType =  null,
                        buttonListener = this::onButtonTap,
                        cellListener = this::onCellTap,
                        leftButtonListener = null,
                        rightButtonListener = null,
                        leftImageListener = null),

                CellModel(id = 3,
                        title = "cell clickable",
                        buttonsType =  ButtonsType.Double,
                        buttonListener = null,
                        leftButtonListener = this::onLeftButtonTap,
                        rightButtonListener = this::onRightButtonTap,
                        cellListener = this::onCellTap,
                        leftImageListener = null)
        )
    }

    private fun onButtonTap(id: Int){
        Toast.makeText(this, "onButtonTap called, id: $id", Toast.LENGTH_LONG).show()
    }

    private fun onRightButtonTap(id: Int){
        Toast.makeText(this, "onRightButtonTap called, id: $id", Toast.LENGTH_LONG).show()
    }

    private fun onLeftButtonTap(id: Int){
        Toast.makeText(this, "onLeftButtonTap called, id: $id", Toast.LENGTH_LONG).show()
    }

    private fun onCellTap(id: Int) {
        Toast.makeText(this, "onCellTap called, id: $id", Toast.LENGTH_LONG).show()
    }

    private fun onLeftImageTap(id: Int){
        Toast.makeText(this, "onProfilePictureTap called, id: $id", Toast.LENGTH_LONG).show()
    }

    private fun onTitleTap(id: Int){
        Toast.makeText(this, "onTitleTap called, id: $id", Toast.LENGTH_LONG).show()
    }
}