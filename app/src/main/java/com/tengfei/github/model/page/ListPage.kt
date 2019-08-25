package com.tengfei.github.model.page

import com.tengfei.common.log.logger
import retrofit2.adapter.rxjava.GitHubPaging
import rx.Observable

/**
 * @author tengfei
 * date 2019-08-25 20:58
 * email tengfeigo@outlook.com
 * description
 */
abstract class ListPage<DateType> : DateProvider<DateType> {

    var currentPage = 1
        private set

    companion object {
        const val PAGE_SIZE = 20
    }

    /**
     * GitHubPaging 也是一个自定义的list
     */
    val date = GitHubPaging<DateType>()

    fun loadMore() = getDate(currentPage + 1).doOnNext {
        currentPage + 1
    }.doOnError {
        logger.error(it.message)
    }.map {
        date.mergeData(it)
        date
    }

    fun loadFromFirst(pageCount: Int) = Observable.range(1, pageCount)
            .concatMap {
                getDate(it)
            }.doOnError {
                logger.error(it.message)
            }.reduce {
                acc, page -> acc.mergeData(page)
            }.doOnNext{
                date.clear()
                date.mergeData(it)
            }
}