package com.tengfei.github.network.service

import com.tengfei.github.entity.Repository
import com.tengfei.github.entity.SearchRepository
import com.tengfei.github.network.retrofit
import retrofit2.adapter.rxjava.GitHubPaging
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * @author tengfei
 * date 2019-08-23 17:07
 * email tengfeigo@outlook.com
 * description 获取仓库列表
 */

interface RepositoryApi {

    /**
     * 获取个人所有的仓库
     * userName : 用户名
     * page ： 页数
     * per_page：每页包含的数量
     */
    @GET("/users/{userName}/repos?type=all")
    fun listRepositoriesOfUser(@Path("userName") userName: String,
                               @Query("page") page: Int = 1,
                               @Query("per_page") per_page: Int = 20): Observable<GitHubPaging<Repository>>


    @GET("/search/repositories?order=desc&sort=updated")
    fun listRepositoriesOfSearch(@Query("q") q: String,
                                 @Query("page") page: Int = 1,
                                 @Query("per_page") per_page: Int = 20): Observable<SearchRepository>
}


object RepositoryService : RepositoryApi by retrofit.create(RepositoryApi::class.java)