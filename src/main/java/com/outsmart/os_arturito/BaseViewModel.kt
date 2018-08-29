package com.outsmart.os_arturito

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.outsmart.academicatlas.OSApplication
import com.outsmart.academicatlas.Utils.Rx.DisposableManager
import io.reactivex.disposables.Disposable

/**
 * Created by rudi eros on 1/26/2018.
 */

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    var initialized = false

    var disposableManager = DisposableManager()


    override fun onCleared() {
        disposableManager.dispose()
        super.onCleared()
    }

    open fun init() {
    }

    fun collectDisposable(disposable: Disposable) {
        disposableManager.add(disposable)
    }
}