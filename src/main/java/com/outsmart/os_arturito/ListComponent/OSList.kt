package com.outsmart.os_arturito.Components.ListComponent

import com.outsmart.os_arturito.Realm.OSRealmList
import io.realm.RealmObject

/**
 * Created by rudieros on 24/08/18.
 */
class OSList<T: ListItem>(
        var pageKey: String? = null,
        var items: List<T> = listOf(),
        var refreshable: Boolean = false,
        var hasMoreItems: Boolean = false,
        var isLoading: Boolean = false
) {
    companion object {
        fun <K: RealmObject, T: ListItem> fromOSRealmList(list: OSRealmList<K>, mapper: (i: Int, k: K) -> T): OSList<T> {
            return OSList(
                    list.pageKey,
                    list.items.mapIndexed(mapper),
                    list.refreshable,
                    list.hasMoreItems,
                    list.isLoading
            )
        }
    }
}