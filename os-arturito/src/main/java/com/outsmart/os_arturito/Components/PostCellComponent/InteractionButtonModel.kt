package com.outsmart.os_arturito.PostCell

data class InteractionButtonModel (
        val buttonPosition: Int,
        var buttonOnClickListener: ((id: Int) -> Unit)?,
        var buttonIsSelected: Boolean = false
){
    var buttonText: String? = ""
    var buttonImage: Int? = null
    var buttonSelectedImage: Int? = null
}

