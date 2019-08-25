package com.tengfei.github.entity

import retrofit2.adapter.rxjava.GitHubPaging

/**
 * @author tengfei
 * date 2019-08-25 18:53
 * email tengfeigo@outlook.com
 * description
 */

abstract class PagingWrapper<T>{

    abstract fun getElements():List<T>

    val paging by lazy {
        GitHubPaging<T>().also {
            it.addAll(getElements())
        }
    }
}