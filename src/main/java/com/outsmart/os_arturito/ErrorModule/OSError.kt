package com.outsmart.os_arturito.ErrorModule

/**
 * Created by appsimples on 8/21/18.
 */
class OSError(): Exception() {
    var type: String = OSErrorTypes.OSBackendUnkownErrorType
    override var message: String = ""
    var devMessage: String = "Backend did not return devMessage"
    var httpCode: Int? = null

    constructor(
            type: String,
            message: String,
            devMessage: String,
            httpCode: Int? = null
    ) : this() {
        this.type = type
        this.message = message
        this.devMessage = devMessage
        this.httpCode = httpCode ?: this.httpCode
    }

    companion object {
        fun createEmptyDataError(): OSError {
            return OSError(
                    OSErrorTypes.EmptyDataError,
                    "Server error",
                    "Backend returned null or undefined data",
                    null
            )
        }
    }
}