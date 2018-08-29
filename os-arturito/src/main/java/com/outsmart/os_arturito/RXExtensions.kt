package com.outsmart.os_arturito

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.outsmart.os_arturito.ErrorModule.OSError
import com.outsmart.os_arturito.Models.RequestResult
import com.outsmart.os_arturito.Models.Status
import com.outsmart.academicatlas.Utils.Rx.DisposableManager
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.realm.Realm

/**
 * Created by rudieros on 19/08/18.
 */

fun <T> Observable<T>.observe(consumer: (t: T) -> Unit, viewModel: BaseViewModel): Disposable {
    val disposable = this.subscribe(consumer)
    viewModel.collectDisposable(disposable)
    return disposable
}

fun Completable.observe(viewModel: BaseViewModel, onComplete: (() -> Unit)? = null, onError: ((error: Throwable) -> Unit)? = null): Disposable {
    val disposable = this.subscribe(
            { onComplete?.invoke() },
            { onError?.invoke(it) }
    )
    viewModel.collectDisposable(disposable)
    return disposable
}

fun Completable.toLiveData(viewModel: BaseViewModel): LiveData<RequestResult<Unit>> {
    val liveData: MutableLiveData<RequestResult<Unit>> = MutableLiveData()
    val disposable = this.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe(
                    { liveData.value = RequestResult(Status.Success) },
                    {
                        liveData.value = RequestResult(Status.Failure, null).apply {
                            it.message?.let { errorCode = it }
                            it.localizedMessage?.let { devMessage = it }
                            (it as? OSError)?.let {
                                errorCode = it.type
                                errorMessage = it.message
                                devMessage = it.devMessage
                            }


                        }
                    }
            )
    viewModel.collectDisposable(disposable)
    return liveData
}

fun Completable.updateLiveData(viewModel: BaseViewModel, liveData: MutableLiveData<RequestResult<Unit>>) {
    val disposable = this.observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe(
                    {
                        liveData.value = RequestResult(Status.Success)
                    },
                    {
                        liveData.value = RequestResult(Status.Failure, null).apply {
                            it.message?.let { errorCode = it }
                            it.localizedMessage?.let { devMessage = it }
                            (it as? OSError)?.let {
                                errorCode = it.type
                                errorMessage = it.message
                                devMessage = it.devMessage
                            }
                        }

                    }
            )
    viewModel.collectDisposable(disposable)
}

fun completableFromRealmTransaction(realm: Realm, transaction: Realm.Transaction): Completable {
    return Completable.create({ emitter ->
        realm.executeTransactionAsync(
                transaction,
                Realm.Transaction.OnSuccess { emitter.onComplete() },
                Realm.Transaction.OnError {
                    it.printStackTrace()
                    emitter.onError(
                            OSError(
                                    "OSRealmTransactionError",
                                    "Ocorreu um erro inesperado", // TODO externalize this
                                    "An error ocurred while trying to execute a realm transaction in a Completable.fromRealmTransaction operation, see stackTrace for more information."
                            ))
                }
        )
    }).closeRealmOnDispose(realm)
}

fun <T> Observable<T>.updateLiveData(liveData: MutableLiveData<T>, viewModel: BaseViewModel) {
    this.observe({ liveData.value = it }, viewModel)
}

fun Disposable.collect(manager: DisposableManager) {
    manager.add(this)
}

fun Completable.closeRealmOnDispose(realm: Realm): Completable {
    this.doOnDispose({
        realm.close()
        realm.cancelTransaction()
    })
    return this
}
