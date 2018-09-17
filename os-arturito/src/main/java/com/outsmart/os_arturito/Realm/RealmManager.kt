package com.outsmart.os_arturito.Realm

import com.outsmart.academicatlas.OSApplication
import com.outsmart.baseproject.Utils.Rx.RealmDisposable
import com.outsmart.os_arturito.ErrorModule.OSError
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Scheduler
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.kotlin.addChangeListener
import io.realm.kotlin.isValid

/**
 * Created by rudieros on 23/08/18.
 */
class RealmManager<C : RealmModel> {
    var results: C? = null
    var changeListener: RealmChangeListener<C>? = null

    fun observeRealmState(clazz: Class<C>, closeInstanceOnComplete: Boolean = true): Observable<C> {
        return Observable.create { emitter: ObservableEmitter<C> ->
            val realm = Realm.getDefaultInstance()
            if (closeInstanceOnComplete.equals(true)) {
                val realmDisposable = RealmDisposable(realm, this::dispose)
                emitter.setDisposable(realmDisposable)
            }
            results = realm.where(clazz).findFirstAsync()
            changeListener = RealmChangeListener {
                if (it.isValid()) emitter.onNext(realm.copyFromRealm(it))
            }
            results?.let { results ->
                changeListener?.let { results.addChangeListener(it) }
            }
        }.subscribeOn(getRealmThread())
                .unsubscribeOn(getRealmThread())
    }

    private fun dispose() {
        results = null
        changeListener = null
    }

    companion object {

        private fun getRealmThread(): Scheduler {
            return OSApplication.realmScheduler
        }

        fun executeTransaction(transaction: Realm.Transaction, closeInstanceOnComplete: Boolean = true): Completable {
            return Completable.create { emitter ->
                val realm = Realm.getDefaultInstance()
                realm.executeTransactionAsync(
                        transaction,
                        Realm.Transaction.OnSuccess {
                            if (closeInstanceOnComplete) {
                                realm.close()
                            }
                            emitter.onComplete()
                        },
                        Realm.Transaction.OnError {
                            if (closeInstanceOnComplete) {
                                realm.close()
                            }
                            it.printStackTrace()
                            emitter.onError(
                                    OSError("OSRealmTransactionError",
                                            "Ocorreu um erro inesperado", // TODO internationalize this
                                            "An error ocurred while trying to execute a realm transaction in a executeTransaction operation, see stackTrace for more information."
                                    ))
                        }
                )
            }
                    .subscribeOn(getRealmThread())
        }
    }


}