package com.outsmart.os_arturito.PostCell

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.component_interaction_button.view.*

fun PostComponent.setupInteractionButtons(postModel: PostComponent, buttonsArray: ArrayList<View>) {

    var buttonLayoutText: TextView
    var buttonLayoutIcon: ImageView

    postModel.interactionButtonsList?.let {
        for (buttonsListLayout in 0 until it.size) {
            buttonLayoutText = buttonsArray[buttonsListLayout].interaction_button_text
            buttonLayoutIcon = buttonsArray[buttonsListLayout].interaction_button_icon

            when (it[buttonsListLayout].buttonText == null) {
                true -> buttonLayoutText.visibility = android.widget.LinearLayout.GONE
                else -> buttonLayoutText.text = it[buttonsListLayout].buttonText
            }
            when (it[buttonsListLayout].buttonImage == null) {
                true -> buttonLayoutIcon.visibility = android.widget.LinearLayout.GONE
                else -> {
                    buttonLayoutIcon.setImageDrawable(it[buttonsListLayout].buttonImage?.let { android.support.v4.content.ContextCompat.getDrawable(buttonLayoutText.context, it) })
                    if (it[buttonsListLayout].buttonSelectedImage != null && it[buttonsListLayout].buttonIsSelected)
                        buttonLayoutIcon.setImageDrawable(it[buttonsListLayout].buttonSelectedImage?.let { android.support.v4.content.ContextCompat.getDrawable(buttonLayoutText.context, it) })
                }
            }

            it[buttonsListLayout].buttonOnClickListener?.let {
                buttonsArray[buttonsListLayout].setOnClickListener {
                    postModel.interactionButtonsList?.get(buttonsListLayout)?.buttonOnClickListener?.invoke(postModel.id)
                }
            }
        }
    }
}







