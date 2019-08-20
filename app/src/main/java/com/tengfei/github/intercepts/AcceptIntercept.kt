package com.tengfei.github.intercepts

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author tengfei
 * date 2019/8/20 3:05 PM
 * email tengfeigo@outlook.com
 * description github api 中的每个接口的header都需要添加 accept
 */

class AcceptIntercept : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(request.newBuilder()
                .apply {
                    header("accept", "application/vnd.github.v3.full+json, ${request.header("accept") ?: ""}")
                }
                .build())
    }
}