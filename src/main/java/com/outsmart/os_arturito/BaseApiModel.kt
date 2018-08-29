package com.outsmart.os_arturito

import io.realm.RealmObject

/**
 * Created by rudieros on 24/08/18.
 */
abstract class BaseApiModel<out AppModel: RealmObject> {
    abstract fun mapToAppModel(): AppModel
}