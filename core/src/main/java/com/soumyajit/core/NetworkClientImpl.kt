package com.soumyajit.core

import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkClientImpl @Inject constructor(
    private val okHttpClientInternal: OkHttpClient
) : NetworkClient {

    private val clientCallFactory: Call.Factory by lazy {
        Call.Factory { request -> okHttpClientInternal.newCall(request) }
    }

    private val retrofitInternal by lazy {
        setupRetrofitBuilder()
    }

    override val retrofit: Retrofit by lazy { retrofitInternal }

    private fun setupRetrofitBuilder(): Retrofit {
        val builder = Retrofit.Builder().callFactory(clientCallFactory)
        return builder.baseUrl("https://baseurl.com").build()
    }
}