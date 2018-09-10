package br.com.appsimples.pulsion.base.repository.remote

import com.outsmart.os_arturito.Auth.OSAuth
import com.outsmart.os_arturito.OSProject
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*


/**
 * Created by outsmart on 18/04/2018.
 */
class OSInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        // TODO log intercepted requests
        chain.request().url()
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("x-api-key", OSProject.config?.apiKey ?: "")
        builder.addHeader("Authorization", OSAuth.token ?: "placeholder_token")
        builder.addHeader("Accept-Language", Locale.getDefault().toString().replace("_", "-")) // TODO change this hideous logic
        return chain.proceed(builder.build())
    }
}