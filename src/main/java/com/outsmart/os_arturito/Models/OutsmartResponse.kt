package com.outsmart.os_arturito.Models

class OutsmartResponse<out T> {
    val data: T? = null
    val error: String? = null
    val message: String? = null
    val status: Int? = null
}
