package com.outsmart.os_arturito.Auth

import com.outsmart.os_arturito.Realm.RealmManager
import com.outsmart.os_arturito.SPManager
import io.reactivex.Completable
import io.reactivex.Observable
import io.realm.Realm

/**
 * Created by appsimples on 8/21/18.
 */
object OSAuth {
    var token: String? = null
        private set
        get() {
            return field ?: SPManager.read(OSAuthConstants.SP_TOKEN)
        }
    var uid: String? = null
        private set
        get() {
            return field ?: SPManager.read(OSAuthConstants.SP_UID)
        }
    private var credentials: String? = null
    private var accessKey: String? = null
    private var secretKey: String? = null
    private var sessionToken: String? = null

    fun cacheAuth(authResponse: OSAuthorizationResponse?) {
        authResponse?.let {
            authResponse.uid?.let { cacheUid(it) }
            when {
                it.tokenRefreshed -> it.token?.let { cacheToken(it) }
                it.credentialsRefreshed -> it.credentials?.let { cacheCredentials(it) }
                else -> {
                }
            }
        }
    }

    private fun cacheUid(uid: String) {
        this.uid = uid
        SPManager.write(OSAuthConstants.SP_UID, uid)
    }

    private fun cacheToken(token: String) {
        this.token = token
        SPManager.write(OSAuthConstants.SP_TOKEN, token)
    }

    private fun cacheCredentials(credentials: String) {
        this.credentials = credentials
        // TODO read credentials token to get actual credential keys
        SPManager.write(OSAuthConstants.SP_CREDENTIALS, credentials)
    }

    fun autoLogin(): Observable<AuthResult> {
        val token = this.token
        if(!token.isNullOrEmpty()){
            return Observable.just(AuthResult(true))

        }
        return Observable.just(AuthResult(false))
    }

    fun logout(): Completable {
        with(SPManager) {
            delete(OSAuthConstants.SP_TOKEN)
            delete(OSAuthConstants.SP_CREDENTIALS)
            delete(OSAuthConstants.SP_UID)
        }
        return Completable.complete()
    }
}
