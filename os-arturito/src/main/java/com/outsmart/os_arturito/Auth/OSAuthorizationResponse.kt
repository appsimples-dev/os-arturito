package com.outsmart.os_arturito.Auth

import com.amazonaws.auth.AWSCredentials

/**
 * Created by appsimples on 8/21/18.
 */
class OSAuthorizationResponse {
    val uid: String? = null
    val token: String? = null
    val credentials: OSCredentials? = null
    val tokenRefreshed: Boolean = false
    val credentialsRefreshed: Boolean = false
}
