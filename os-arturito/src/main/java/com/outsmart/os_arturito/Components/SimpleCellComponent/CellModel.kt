package com.outsmart.os_arturito.SimpleCell

data class CellModel(
        val id: Int,
        val title: String,
        val subTitle: String? = null,
        val leftImage: String? = null,
        val secondSubtitle: String? = null,
        val buttonsType: ButtonsType?,
        val buttonListener: ((id: Int) -> Unit)?,
        val rightButtonListener: ((id: Int) -> Unit)?,
        val leftButtonListener: ((id: Int) -> Unit)?,
        val cellListener: ((id: Int) -> Unit)?,
        val leftImageListener: ((id: Int) -> Unit)?,
        val titleListener: ((id: Int) -> Unit)?
)

enum class ButtonsType {
    Single, Double
}