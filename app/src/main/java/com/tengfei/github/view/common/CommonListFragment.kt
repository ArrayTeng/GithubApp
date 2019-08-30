package com.tengfei.github.view.common

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter
import com.tengfei.github.R
import com.tengfei.github.model.page.ListPage
import com.tengfei.github.view.widget.ErrorInfoView
import com.tengfei.mvp.impl.BaseFragment
import kotlinx.android.synthetic.main.list.*
import org.jetbrains.anko.support.v4.toast
import retrofit2.adapter.rxjava.GitHubPaging

/**
 * @author tengfei
 * date 2019-08-28 15:35
 * email tengfeigo@outlook.com
 * description
 */

abstract class CommonListFragment<DataType, out Presenter : CommonListPresenter<DataType,
        CommonListFragment<DataType, Presenter>>> : BaseFragment<Presenter>() {

    public abstract val adapter: CommonListAdapter<DataType>

    public val errorInfoView by lazy {
        ErrorInfoView(rootView)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshView.setColorSchemeResources(R.color.google_blue, R.color.google_green, R.color.google_red, R.color.google_yellow)
        recyclerView.adapter = LuRecyclerViewAdapter(adapter)
        refreshView.isRefreshing = true
        recyclerView.setLoadMoreEnabled(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.itemAnimator = DefaultItemAnimator()

        recyclerView.setOnLoadMoreListener(presenter::loadMore)
        refreshView.setOnRefreshListener(presenter::refreshData)

        presenter.initData()

    }

    fun initData(data: GitHubPaging<DataType>) {
        adapter.data.clear()
        adapter.data.addAll(data)
        refreshView.isRefreshing = false
        //设置是否已加载全部
        recyclerView.setNoMore(data.isLast)
        //一页加载的数量
        recyclerView.refreshComplete(ListPage.PAGE_SIZE)
        dismissError()
    }

    fun initDataWithNothing() {
        refreshView.isRefreshing = false
        recyclerView.setNoMore(true)
        recyclerView.refreshComplete(ListPage.PAGE_SIZE)
        showError("No data")
        errorInfoView.isClickable = false
    }

    fun initDataWithError(errorInfo: String) {
        refreshView.isRefreshing = false
        recyclerView.setNoMore(true)
        recyclerView.refreshComplete(ListPage.PAGE_SIZE)
        showError(errorInfo)
        errorInfoView.isClickable = true
        errorInfoView.setOnClickListener {
            presenter.initData()
        }
    }

    fun initdataWithMore(data: GitHubPaging<DataType>){
        adapter.data.update(data)
        recyclerView.refreshComplete(ListPage.PAGE_SIZE)
        recyclerView.setNoMore(data.isLast)
        dismissError()
    }


    fun ondataRefreshDataWithError(errorInfo: String) {
        if (adapter.data.isEmpty()) {
            showError(errorInfo)
            errorInfoView.isClickable = true
            errorInfoView.setOnClickListener {
                presenter.initData()
            }
        } else {
            toast("data is empty")
            refreshView.isRefreshing = false
        }
    }

    fun refreshData(data: GitHubPaging<DataType>) {
        initData(data)
    }

    open fun dismissError() {
        errorInfoView.dismiss()
    }

    open fun showError(errorInfo: String) {
        errorInfoView.show(errorInfo)
    }
}