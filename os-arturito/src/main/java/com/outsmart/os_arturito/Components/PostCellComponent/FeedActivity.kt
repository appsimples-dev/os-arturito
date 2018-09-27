package com.outsmart.os_arturito.PostCell

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.outsmart.os_arturito.Components.ListComponent.ListItem
import com.outsmart.os_arturito.Components.ListComponent.OSList
import com.outsmart.os_arturito.R
import com.outsmart.os_arturito.Realm.OSRealmList

class FeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
    }

    var mapPostAppToView: (realmList: OSRealmList<OSArturitoPostAppModel>) -> OSList<ListItem> = { realmList: OSRealmList<OSArturitoPostAppModel> ->
        OSList.fromOSRealmList(realmList, { i, postApp ->
            PostComponent(
                    i.toString(),
                    postApp.posterName,
                    postApp.body,
                    postApp.date,
                    "",
                    postApp.avatar,
                    postApp.postImageUrl,
                    interactionButtons(
                            text = arrayOf(postApp.likeCounter.toString(), postApp.commentCounter.toString(), null, "Novo!", null),
                            isSelected = arrayOf(postApp.likedByMe, false, false, false, false))
            )
        })
    }

    private fun interactionButtons(text: Array<String?>, isSelected: Array<Boolean>): List<InteractionButtonModel> {
        return listOf(
                //LIKE BUTTON - Icon and Counter
                InteractionButtonModel(
                        buttonPosition = 0,
                        buttonOnClickListener = this::onPressButton0,
                        buttonIsSelected = isSelected[0]
                ).apply {
                    buttonSelectedImage = R.drawable.liked
                    buttonImage = R.drawable.like
                    buttonText = text[0]
                },
                //COMMENTS BUTTON - Icon and counter
                InteractionButtonModel(
                        buttonPosition = 1,
                        buttonOnClickListener = this::onPressButton1,
                        buttonIsSelected = isSelected[1]
                ).apply {
                    buttonSelectedImage = null
                    buttonImage = R.drawable.comment
                    buttonText = text[1]
                },
                //Icon only Button
                InteractionButtonModel(
                        buttonPosition = 2,
                        buttonOnClickListener = this::onPressButton2,
                        buttonIsSelected = isSelected[2]
                ).apply {
                    buttonSelectedImage = R.drawable.liked
                    buttonImage = R.drawable.like
                    buttonText = text[2]
                },
                //Text Only Button
                InteractionButtonModel(
                        buttonPosition = 3,
                        buttonOnClickListener = this::onPressButton3,
                        buttonIsSelected = isSelected[3]
                ).apply {
                    buttonSelectedImage = null
                    buttonImage = null
                    buttonText = text[3]
                },
                //SHARE BUTTON - Image Only
                InteractionButtonModel(
                        buttonPosition = 4,
                        buttonOnClickListener = this::onPressButton4,
                        buttonIsSelected = isSelected[4]
                ).apply {
                    buttonSelectedImage = null
                    buttonImage = R.drawable.share
                    buttonText = text[4]
                })
    }

    private fun onPressButton0(id: Any) {
        //TODO: implement
        Toast.makeText(this, "onPressButton0 called, id: $id", Toast.LENGTH_LONG).show()
    }

    private fun onPressButton1(id: Any) {
        //TODO: implement
        Toast.makeText(this, "onPressButton1 called, id: $id", Toast.LENGTH_LONG).show()
    }

    private fun onPressButton2(id: Any) {
        //TODO: implement
        Toast.makeText(this, "onPressButton2 called, id: $id", Toast.LENGTH_LONG).show()
    }

    private fun onPressButton3(id: Any) {
        //TODO: implement
        Toast.makeText(this, "onPressButton3 called, id: $id", Toast.LENGTH_LONG).show()
    }

    private fun onPressButton4(id: Any) {
        //TODO: implement
        Toast.makeText(this, "onPressButton4 called, id: $id", Toast.LENGTH_LONG).show()
    }
}
