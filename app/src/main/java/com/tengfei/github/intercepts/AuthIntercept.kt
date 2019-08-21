package com.tengfei.github.intercepts

import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Base64
import com.tengfei.github.model.account.AccountManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author tengfei
 * date 2019/8/19 8:42 PM
 * email tengfeigo@outlook.com
 * description github 每个接口都是需要鉴权的，定义鉴权拦截器
 */
class AuthIntercept : Interceptor {
    @RequiresApi(Build.VERSION_CODES.FROYO)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(request.newBuilder()
                .apply {
                    when {
                        request.url().pathSegments().contains("authorizations") -> {
                            //用用户登录后的用户名密码拼接字符串
                            val userCredentials = AccountManager.userName + ":" + AccountManager.passWord
                            val auth = "Basic " + String(Base64.encode(userCredentials.toByteArray(), Base64.DEFAULT)).trim()
                            header("Authorization", auth)
                        }

                        AccountManager.isLogin() -> {
                            val auth = "Token " + AccountManager.token
                            header("Authorization", auth)
                        }

                        else -> {
                            removeHeader("Authorization")
                        }
                    }
                }
                .build())
    }
}