package com.mvenosa.os_components_android

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.outsmart.os_arturito.PostCell.FeedActivity
import com.outsmart.os_arturito.SimpleCell.CellsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setupButtons()
    }

    private fun setupButtons(){
        cells_component.setOnClickListener({
            startActivity(Intent(this, CellsActivity::class.java))
        })
        posts_component.setOnClickListener({
            startActivity(Intent(this, FeedActivity::class.java))
        })
    }
}
