package com.tengfei.github.network.service

import com.tengfei.github.entity.AccountEntitiesRequest
import com.tengfei.github.entity.AccountEntitiesResponse
import com.tengfei.github.network.retrofit
import com.tengfei.github.settings.Configs
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path
import rx.Observable

/**
 * @author tengfei
 * date 2019/8/19 2:28 PM
 * email tengfeigo@outlook.com
 * description
 */
interface AuthApi {

    /**
     * 获取和创建鉴权
     */
    @PUT("/authorizations/clients/${Configs.Account.clientId}/{fingerprint}")
    fun createAuthorization(@Body req: AccountEntitiesRequest,
                            @Path("fingerprint") fingerprint: String = Configs.Account.fingerPrint)
            : Observable<AccountEntitiesResponse>


    /**
     * 删除鉴权
     */
    @DELETE("/authorizations/{authorization_id}")
    fun deleteAuthorization(@Path("authorization_id") authorizationId: Int): Observable<Response<Any>>

}

// https://www.runoob.com/kotlin/kotlin-delegated.html

/**
 * AuthService实现了 AuthApi 接口并通过关键字 by 来建立委托类，由委托类来执行 AuthApi 接口里的方法
 */
object AuthService : AuthApi by retrofit.create(AuthApi::class.java)

