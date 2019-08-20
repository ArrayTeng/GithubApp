package com.tengfei.github.network.service

import com.tengfei.github.entity.User
import com.tengfei.github.network.retrofit
import retrofit2.http.GET
import rx.Observable

/**
 * @author tengfei
 * date 2019/8/19 8:35 PM
 * email tengfeigo@outlook.com
 * description
 */

interface UserApi {

    /**
     * 获取鉴权User response
     */
    @GET("/user")
    fun getAuthenticatedUser(): Observable<User>
}


object UserService : UserApi by retrofit.create(UserApi::class.java)