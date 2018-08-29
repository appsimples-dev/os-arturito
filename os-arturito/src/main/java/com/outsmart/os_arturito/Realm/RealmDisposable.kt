package com.outsmart.baseproject.Utils.Rx

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.realm.Realm

/**
 * Created by rudi eros on 1/28/2018.
 */

class RealmDisposable(val realm: Realm, var onDispose: (() -> Unit)? = null) : Disposable {
    private var isDisposed: Boolean = false

    override fun dispose() {
        with(this.realm) {
            if (!isClosed) {
                removeAllChangeListeners()
                close()
                onDispose?.invoke()
            }
        }
        this.isDisposed = true
    }

    override fun isDisposed(): Boolean {
        return this.isDisposed
    }

    companion object {

        fun getDisposableAction(disposable: RealmDisposable): Action {
            return Action { disposable.dispose() }
        }
    }
}