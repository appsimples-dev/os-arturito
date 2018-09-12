package com.outsmart.os_arturito

import com.google.gson.Gson
import com.outsmart.academicatlas.OSApplication
import com.outsmart.academicatlas.Utils.Rx.DisposableManager
import com.outsmart.os_arturito.Auth.OSAuth
import com.outsmart.os_arturito.ErrorModule.OSError
import com.outsmart.os_arturito.Models.OSResponse
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

/**
 * Created by appsimples on 8/14/18.
 */

open class BaseService {

    companion object {

        fun <ApiModel : BaseApiModel<AppModel>, AppModel> makeApiCall(
                remote: Single<OSResponse<ApiModel>>,
                local: ((appModel: AppModel) -> Completable)? = null): Single<AppModel> {
            return Single.create {
                val d = DisposableManager()
                it.setDisposable(d)
                remote.subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe({ response ->
                            when {
                                response.error != null -> it.onError(response.error)
                                response.data == null -> it.onError(OSError.createEmptyDataError())
                                else -> {
                                    // successful response, cache authorization
                                    OSAuth.cacheAuth(response.authorization)
                                    val data = response.data.mapToAppModel()
                                    // if a completable to save data locally was passed, wait for it to complete
                                    try {
                                        local?.invoke(data)?.subscribeOn(OSApplication.realmScheduler)?.blockingAwait()
                                    } catch (throwable: Throwable) {
                                        it.onError(throwable)
                                    }
                                    it.onSuccess(response.data.mapToAppModel())
                                }
                            }
                        }, { t ->
                            if (t is HttpException) {
                                t.printStackTrace()
                                it.onError(listenError(t))
                            }
                        }).collect(d)
            }
        }


        private fun listenError(exception: HttpException): OSError {
            val body: String = ((exception).response().errorBody())?.string() ?: ""
            var error = OSError(exception.message(), exception.localizedMessage, "The error returned by the backend was not recognized to be generated by Outsmart backend.")
            try {
                // check if body is OSResponse
                error = parseErrorBody(body).error ?: error
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
            return error
        }

        private fun parseErrorBody(bodyJSON: String): OSResponse<*> {
            val error: OSResponse<*> = Gson().fromJson(bodyJSON, OSResponse::class.java)
            return error
        }
    }
}