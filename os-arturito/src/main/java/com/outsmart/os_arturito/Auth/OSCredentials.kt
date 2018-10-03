package com.outsmart.os_arturito.Auth

import com.amazonaws.auth.AWSCredentials

class OSCredentials: AWSCredentials {
    var uiid: String? = null
    var accessKeyId: String? = null
    var secretKey: String? = null
    var sessionToken: String? = null

    override fun getAWSAccessKeyId(): String? {
        return accessKeyId
    }

    override fun getAWSSecretKey(): String? {
        return secretKey
    }

}