package com.outsmart.os_arturito

import com.outsmart.os_arturito.Realm.OSRealmList
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by rudieros on 24/08/18.
 */
abstract class OSListApiModel<out ListAppModel, ItemApiModel : RealmObject, out ItemAppModel : BaseApiModel<ItemApiModel>> : BaseApiModel<ListAppModel>() where ListAppModel : RealmObject, ListAppModel : OSRealmList<ItemApiModel> {

    override fun mapToAppModel(): ListAppModel {
        val realmList: RealmList<ItemApiModel> = RealmList()
        val mappedList: List<ItemApiModel> = this.items.map { it.mapToAppModel() }
        realmList.addAll(mappedList)
        with(mapToAppOSList()) {
            items = realmList
            pageKey = pageKey
            hasMore = hasMore
            return this
        }
    }

    abstract fun mapToAppOSList(): ListAppModel

    private val items: List<ItemAppModel> = listOf()
    val pageKey: String = ""
    private val hasMore: Boolean = false
}