package com.outsmart.os_arturito.PostCell

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class OSArturitoPostAppModel : RealmObject() {
    @PrimaryKey
    var postId: String = ""

    var posterId: String = ""
    var posterName: String = ""
    var body: String = ""
    var postImageUrl: String = ""
    var avatar: String = ""
    var likedByMe: Boolean = false
    var commentedByMe: Boolean = false
    var likeCounter: Long = 0
    var commentCounter: Long = 0
    var date: Date? = null
}
