package com.tengfei.github.model.page

import retrofit2.adapter.rxjava.GitHubPaging
import rx.Observable

/**
 * @author tengfei
 * date 2019-08-25 20:52
 * email tengfeigo@outlook.com
 * description
 */
interface DateProvider<DateType> {

    fun getDate(page: Int): Observable<GitHubPaging<DateType>>
}