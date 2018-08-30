package com.outsmart.os_arturito.PostCell


import com.outsmart.os_arturito.BaseApiModel
import java.util.*

class PostApiModel: BaseApiModel<PostAppModel>() {

    var id: String = ""
    var posterId: String = ""
    var userName: String = ""
    var createdAt: Date = Date()
    var name: String = ""
    var avatar: String = ""
    var imageUrl: String = ""
    var likedByMe: Boolean = false
    var commentCount: Long = 0
    var likeCount: Long = 0
    var title: String = ""
    var body: String = ""

    override fun mapToAppModel(): PostAppModel {
        val postApiModel = this
        val appModel = PostAppModel()
        appModel.posterName = postApiModel.userName
        appModel.postImageUrl = "https://i.redditmedia.com/ElMktJSYVFgrsZD4BtSP9SLDh5MI4aVBrStTEkJW56E.jpg?fit=crop&crop=faces%2Centropy&arh=2&w=960&s=e9eb6fb0bd9eb757f448c1d82b1583ad"
        appModel.posterId = postApiModel.posterId
        appModel.avatar = postApiModel.avatar
        appModel.body = postApiModel.body
        appModel.postId = postApiModel.id
        appModel.commentCounter = postApiModel.commentCount
        appModel.likeCounter = postApiModel.likeCount
        appModel.likedByMe = postApiModel.likedByMe
        return appModel
    }
}
