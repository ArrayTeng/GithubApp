package com.tengfei.github.view.common

import com.tengfei.github.model.page.ListPage
import com.tengfei.mvp.impl.BasePresenter
import rx.Subscription

/**
 * @author tengfei
 * date 2019-08-28 15:35
 * email tengfeigo@outlook.com
 * description
 */

abstract class CommonListPresenter<DataType, out View : CommonListFragment<DataType, CommonListPresenter<DataType, View>>> : BasePresenter<View>() {


    abstract val listPage: ListPage<DataType>

    private val subscriptionList: ArrayList<Subscription> = ArrayList()

    private var isRefresh: Boolean = true

    fun initData() {
        listPage.loadFromFirst().subscribe({
            view.initData(it)
        }, {
            view.initDataWithError(it.message ?: "error")
        }).let(subscriptionList::add)
    }

    fun loadMore() {
        listPage.loadMore().subscribe({
            view.initdataWithMore(it)
        }, {
            view.ondataRefreshDataWithError(it.message ?: "error")
        }).let(subscriptionList::add)
    }

    fun refreshData() {
        listPage.loadFromFirst().subscribe({
            if (it.isEmpty()) {
                view.initDataWithNothing()
            } else {
                view.refreshData(it)
            }

        }, {
            view.ondataRefreshDataWithError(it.message ?: "error")
        }).let(subscriptionList::add)
    }

    override fun onResume() {
        super.onResume()
        if (!isRefresh) {
            refreshData()
        }
        isRefresh = false
    }

    override fun onDestroy() {
        subscriptionList.forEach(Subscription::unsubscribe)
        subscriptionList.clear()
        super.onDestroy()
    }
}