package com.outsmart.os_arturito.Realm

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by rudieros on 24/08/18.
 */
interface OSRealmList<T: RealmObject> {
    var pageKey: String?
    var items: RealmList<T>
    var refreshable: Boolean
    var hasMoreItems: Boolean
    var isLoading: Boolean

    fun getList(): List<T> {
        return items.toList()
    }
}