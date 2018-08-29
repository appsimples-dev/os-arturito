package com.outsmart.os_arturito.Models

import com.outsmart.os_arturito.Auth.OSAuthorizationResponse
import com.outsmart.os_arturito.ErrorModule.OSError

class OSResponse<out T> {
    val data: T? = null
    val error: OSError? = null
    val message: String? = null
    val status: Int? = null
    val authorization: OSAuthorizationResponse? = null
}
