package com.outsmart.academicatlas

import android.app.Application
import android.os.HandlerThread
import android.util.DisplayMetrics
import com.outsmart.academicatlas.Utils.Rx.DisposableManager
import com.outsmart.os_arturito.Realm.OSArturitoModule
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by rudi eros on 1/24/2018.
 */

open class OSApplication : Application() {

    private val disposableManager = DisposableManager()

    lateinit var realmConfig: RealmConfiguration

    override fun onCreate() {
        super.onCreate()

        realmIo.start()
        OSApplication.realmScheduler = AndroidSchedulers.from(realmIo.looper)
        instance = this
    }

    override fun onTerminate() {
        disposableManager.dispose()
        super.onTerminate()
    }

    companion object {

        internal val realmIo = HandlerThread("realm")
        lateinit var realmScheduler: Scheduler

        var displayMetrics: DisplayMetrics? = null
            private set

        lateinit var instance: OSApplication
    }


}
