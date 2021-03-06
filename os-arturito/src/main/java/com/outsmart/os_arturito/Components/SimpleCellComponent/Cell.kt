package com.outsmart.os_arturito.SimpleCell

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.outsmart.os_arturito.GlideApp
import com.outsmart.os_arturito.R
import kotlinx.android.synthetic.main.component_cell.view.*

class Cell @JvmOverloads constructor (context: Context) {

    private var cellModel : CellModel? = null
    var view: View? = null

    companion object {
        fun withView(view: View, context: Context): Cell {
            val cell = Cell(context)
            cell.view = view
            return cell
        }
    }

    fun setModel(cellModel: CellModel) {
        this.cellModel = cellModel
        setupButtons()
        setupTexts()
        setupLeftImage()
    }

    private fun setupButtons() {
        if (cellModel?.buttonsType == ButtonsType.Single) {
            view?.component_cell_center_button?.visibility = View.VISIBLE
            if(cellModel?.buttonListener != null) view?.component_cell_center_button?.setOnClickListener({
                cellModel?.buttonListener?.invoke(cellModel?.id ?: 0)
            }) else {
                view?.component_cell_center_button?.visibility = View.GONE
            }
            if(cellModel?.cellListener != null) view?.component_cell_root?.setOnClickListener({
                cellModel?.cellListener?.invoke((cellModel?.id ?: 0))
            })

        } else if (cellModel?.buttonsType == ButtonsType.Double) {
            view?.component_cell_left_button?.visibility = View.VISIBLE
            view?.component_cell_right_button?.visibility = View.VISIBLE
            if (cellModel?.rightButtonListener != null && cellModel?.leftButtonListener != null) {
                view?.component_cell_left_button?.setOnClickListener({
                    cellModel?.leftButtonListener?.invoke(cellModel?.id ?: 0)
                })
                view?.component_cell_right_button?.setOnClickListener({
                    cellModel?.rightButtonListener?.invoke(cellModel?.id ?: 0)
                })
            } else {
                view?.component_cell_left_button?.visibility = View.GONE
                view?.component_cell_right_button?.visibility = View.GONE
            }
            if (cellModel?.cellListener != null) view?.component_cell_root?.setOnClickListener({
                cellModel?.cellListener?.invoke((cellModel?.id ?: 0))
            })
        }
    }

    fun setTitleTextSelectable(isTitleSelectable: Boolean){
        view?.component_cell_title?.setTextIsSelectable(isTitleSelectable)
    }

    fun setSubtitleSelectable(isSubtitleSelectable: Boolean) {
        view?.component_cell_subtitle?.setTextIsSelectable(isSubtitleSelectable)
        view?.component_cell_subtitle2?.setTextIsSelectable(isSubtitleSelectable)
    }

    private fun setupTexts() {
        view?.component_cell_title?.text = cellModel?.title
        if (cellModel?.titleListener != null){
            view?.component_cell_title?.setOnClickListener {
                cellModel?.titleListener?.invoke(cellModel?.id ?: 0)
            }
        }
        if(!cellModel?.subTitle.isNullOrEmpty()) view?.component_cell_subtitle?.text = cellModel?.subTitle
        else view?.component_cell_subtitle?.visibility = View.GONE
        if(!cellModel?.secondSubtitle.isNullOrEmpty()) view?.component_cell_subtitle2?.text = cellModel?.secondSubtitle
        else view?.component_cell_subtitle2?.visibility = View.GONE
    }

    fun setButtonText(buttonText: String){
        view?.component_cell_center_button?.text = buttonText
    }

    private fun setupProfilePicture(profilePicture: ImageView) {
        if (!cellModel?.leftImage.isNullOrEmpty()) {
            GlideApp
                    .with(profilePicture.context)
                    .load(cellModel?.leftImage)
                    .centerCrop()
                    .transform(CircleCrop())
                    .placeholder(R.drawable.profileplaceholder).dontAnimate()
                    .into(profilePicture)
        }
    }

    private fun setupLeftImage(){
        view?.component_cell_image_container?.visibility = View.GONE
        if (!cellModel?.leftImage.isNullOrEmpty()){
            view?.component_cell_image_container?.visibility = View.VISIBLE
            view?.component_cell_image?.let { setupProfilePicture(it) }
            if (cellModel?.leftImageListener != null){
                view?.component_cell_image?.setOnClickListener {
                    cellModel?.leftImageListener?.invoke(cellModel?.id ?: 0)
                }
            }
        }
    }

}