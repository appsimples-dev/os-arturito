package com.outsmart.os_arturito.SimpleCell

data class CellModel(
        val id: Int,
        val title: String,
        val subTitle: String? = null,
        val secondSubtitle: String? = null,
        val buttonsType: ButtonsType?,
        val buttonListener: ((id: Int) -> Unit)?,
        val cellListener: ((id: Int) -> Unit)?
)

enum class ButtonsType {
    Single, Double
}