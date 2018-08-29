package com.outsmart.os_arturito

import br.com.appsimples.pulsion.base.repository.remote.OSInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by appsimples on 8/14/18.
 */

class ServerApiBuilder {

    companion object {
        private const val CONNECTION_TIMEOUT = 120L
    }

    object Header {
        const val AUTHORIZATION = "Authorization"
    }

    /**
     * Builds a service which complies with an Outsmart server
     * @param baseUrl: Url of the api
     * @param
     */
    fun <T> buildOSService(baseUrl: String, apiInterface: Class<T>): T {
        val httpClient = OkHttpClient.Builder()
                .addInterceptor(OSInterceptor())
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .build()

        return buildRetrofit(baseUrl, httpClient).create(apiInterface)
    }

    fun <T> build(baseUrl: String, apiInterface: Class<T>): T {
        val httpClient = OkHttpClient.Builder()
                .addInterceptor(OSInterceptor())
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .build()

        return buildRetrofit(baseUrl, httpClient).create(apiInterface)
    }

    private fun createLogLevelInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        val logLevel = getLogLevel()
        httpLoggingInterceptor.level = logLevel

        return httpLoggingInterceptor
    }


    private fun createRequestHeadersInterceptor(authToken: String): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val request = originalRequest
                    .newBuilder()
                    .header(Header.AUTHORIZATION, authToken)
                    .method(originalRequest.method(), originalRequest.body())
                    .build()
            chain.proceed(request)
        }
    }

    private fun getLogLevel() = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

    private fun buildRetrofit(baseUrl: String, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}