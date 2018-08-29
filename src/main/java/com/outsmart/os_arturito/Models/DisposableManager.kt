package com.outsmart.academicatlas.Utils.Rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action

/**
 * Created by rudi eros on 11/5/2017.
 */

class DisposableManager : Disposable {
    private var compositeDisposable: CompositeDisposable? = null
    private var isDisposed: Boolean = false

    fun add(disposable: Disposable) {
        getCompositeDisposable().add(disposable)
    }

    override fun dispose() {
        this.isDisposed = true
        getCompositeDisposable().dispose()
    }

    override fun isDisposed(): Boolean {
        return this.isDisposed
    }

    private fun getCompositeDisposable(): CompositeDisposable {
        compositeDisposable?.let {
            if (it.isDisposed) {
                compositeDisposable = CompositeDisposable()
            }
        }
        return compositeDisposable ?: CompositeDisposable()
    }

    companion object {
        fun getDisposableAction(disposable: DisposableManager): Action {
            return Action { disposable.dispose() }
        }
    }
}
