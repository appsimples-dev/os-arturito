package com.outsmart.os_arturito.PostCell

import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.module.AppGlideModule
import com.outsmart.os_arturito.Components.ListComponent.ListItem
import com.outsmart.os_arturito.GlideApp
import com.outsmart.os_arturito.R
import douglasspgyn.com.github.doubletapview.DoubleTapView
import kotlinx.android.synthetic.main.component_post_cell.view.*
import kotlinx.android.synthetic.main.component_post_interaction.view.*
import kotlinx.android.synthetic.main.component_post_user.view.*
import java.util.*

typealias PostComponentAction = (component: PostComponent) -> Unit

open class PostComponent(
        val id: Int,
        var name: String = "",
        var description: String = "",
        var date: Date? = null,
        var subTitle: String? = "",
        var profilePicture: String? = "",
        var media: String? = "",
        var interactionButtonsList: List<InteractionButtonModel>? = null,
        var mediaOnClickListener: ((id: Int) -> Unit)? = null,
        var reportPostOnClickListener: ((id: Int) -> Unit)? = null,
        var removePostOnClickListener: ((id: Int) -> Unit)? = null,
        var profilePictureOnClickListener: ((id: Int) -> Unit)? = null,
        var userNameOnClickListener: ((id: Int) -> Unit)? = null
): ListItem() {

    companion object {
        var settingsIconIsHorizontal: Boolean = false
    }

    override fun bindView(view: View) {
        setupProfilePicture(view.post_cell_profile_picture)
        setupMedia(view.post_cell_media)
        setupPostInfo(view.post_cell_user_name, view.postDate, view.postSubTitle, view.post_cell_description)
        setupClickableItems(view.post_cell_profile_picture, view.post_cell_user_name, view.post_cell_media)
        setupSettingsMenu(settingsIconIsHorizontal, view.post_cell_settings_icon, view)
        val buttonsListLayout = arrayListOf(
                view.post_cell_button0,
                view.post_cell_button1,
                view.post_cell_button2,
                view.post_cell_button3,
                view.post_cell_button4)
        setupInteractionButtons(this, buttonsListLayout)
    }

    fun buttonForIndex(index: Int): InteractionButtonModel? {
        return interactionButtonsList?.get(index)
    }

    private fun setupProfilePicture(profilePicture: ImageView) {
        if (!this.profilePicture.isNullOrEmpty()) {
            GlideApp
                    .with(profilePicture.context)
                    .load(this.profilePicture)
                    .centerCrop()
                    .transform(CircleCrop())
                    .placeholder(R.drawable.profileplaceholder).dontAnimate()
                    .into(profilePicture)
        }
    }

    private fun setupMedia(postMedia: DoubleTapView) {
        if (!this.media.isNullOrEmpty()) {
              GlideApp.with(postMedia.context)
                    .load(this.media)
                    .centerCrop()
                    .placeholder(R.drawable.image_placeholder).dontAnimate()
                    .into(postMedia.backgroundImageView)
        } else {
            postMedia.visibility = View.GONE
        }
    }

    private fun setupPostInfo(username: TextView, date: TextView, subTitle: TextView, description: TextView) {
        username.text = this.name
        date.text = this.date?.toString() ?: ""

        when (!this.subTitle.isNullOrEmpty()) {
            true -> subTitle.text = this.subTitle
            else -> subTitle.visibility = View.GONE
        }
        when (!this.description.isNullOrEmpty()) {
            true -> description.text = this.description
            else -> description.visibility = View.GONE
        }
    }

    private fun setupSettingsMenu(iconIsHorizontal: Boolean, cellSettingsIcon: ImageView, view: View) {
        when (iconIsHorizontal){
            true -> view.post_cell_settings_icon.rotation = 90f
        }
        cellSettingsIcon.setOnClickListener {
            val popup = PopupMenu(cellSettingsIcon.context, cellSettingsIcon)
            popup.inflate(R.menu.menu_settings)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.post_cell_menu_report_option -> {
                        this.reportPostOnClickListener?.invoke(this.id)
                    }
                    R.id.post_cell_menu_remove_option -> {
                        this.removePostOnClickListener?.invoke(this.id)
                    }
                }
                false
            }
            popup.show()
        }
    }

    private fun setupClickableItems(profilePicture: ImageView, username: TextView, media: DoubleTapView) {
        this.profilePictureOnClickListener?.let {
            profilePicture.setOnClickListener {
                this.profilePictureOnClickListener?.invoke(this.id)
            }
        }
        this.userNameOnClickListener?.let {
            username.setOnClickListener {
                this.userNameOnClickListener?.invoke(this.id)
            }
        }
        this.mediaOnClickListener?.let {
            media.setOnDoubleTapEventListener {
                this.mediaOnClickListener?.invoke(this.id)
            }
        }
    }
}
