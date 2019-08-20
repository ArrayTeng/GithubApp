package com.tengfei.github.network

import com.tengfei.common.ext.ensureDir
import com.tengfei.github.AppContext
import com.tengfei.github.intercepts.AcceptIntercept
import com.tengfei.github.intercepts.AuthIntercept
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author tengfei
 * date 2019/8/19 3:00 PM
 * email tengfeigo@outlook.com
 * description
 */

private const val BASE_URL = "https://api.github.com"

private val cacheFile by lazy {
    File(AppContext.cacheDir, "webService").apply {
        ensureDir()
    }
}

private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .cache(Cache(cacheFile, 1024 * 1024 * 1024))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(AuthIntercept())
        .addInterceptor(AcceptIntercept())
        .build()


@Suppress("HasPlatformType")
val retrofit by lazy {
    Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient)
            .build()
}