package com.tengfei.github.intercepts

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author tengfei
 * date 2019/8/19 8:42 PM
 * email tengfeigo@outlook.com
 * description
 */
class AuthIntercept:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(request.newBuilder()
                .apply {

                }
                .build())
    }
}