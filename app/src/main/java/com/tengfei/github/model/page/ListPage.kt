package com.tengfei.github.model.page

import com.tengfei.common.log.logger
import retrofit2.adapter.rxjava.GitHubPaging
import rx.Observable

/**
 * @author tengfei
 * date 2019-08-25 20:58
 * email tengfeigo@outlook.com
 * description 对列表页面加载更多和刷新的操作，如果你的页面是一个列表可以使用它
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

    /**
     * 加载更多
     */
    fun loadMore(): Observable<GitHubPaging<DateType>> = getDate(currentPage + 1).doOnNext {
        currentPage + 1
    }.doOnError {
        logger.error(it.message)
    }.map {
        date.mergeData(it)
        date
    }

    /**
     * 从第一页开始加载到 pageCount 页面，返回的数据是这些页面的总和
     */
    fun loadFromFirst(pageCount: Int = currentPage): Observable<GitHubPaging<DateType>> = Observable.range(1, pageCount)
            .concatMap {
                getDate(it)
            }.doOnError {
                logger.error(it.message)
            }.reduce { acc, page ->
                acc.mergeData(page)
            }.doOnNext {
                date.clear()
                date.mergeData(it)
            }
}