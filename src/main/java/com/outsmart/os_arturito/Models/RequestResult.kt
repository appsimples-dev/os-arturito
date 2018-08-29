package com.outsmart.os_arturito.Models

import com.outsmart.os_arturito.R
import com.outsmart.academicatlas.OSApplication

/**
 * Created by appsimples on 8/22/18.
 */
open class RequestResult<out T>(
        val status: Status,
        val data: T? = null
) {
    var errorCode: String = "UnkownErrorFromException"
    var errorMessage: String = OSApplication.instance.getString(R.string.unexpected_error)
    var devMessage: String = "No developer message was provided for this error, this is a default message from Outsmart Android library"
}