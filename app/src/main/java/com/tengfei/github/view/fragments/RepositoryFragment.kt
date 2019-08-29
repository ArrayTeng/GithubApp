package com.tengfei.github.view.fragments

import com.tengfei.github.entity.Repository
import com.tengfei.github.presenter.RepositoryPresenter
import com.tengfei.github.view.common.CommonListFragment
import com.tengfei.github.view.common.CommonRecyclerAdapter
import com.tengfei.mvp.impl.BaseFragment

/**
 * @author tengfei
 * date 2019-08-29 17:48
 * email tengfeigo@outlook.com
 * description
 */
class RepositoryFragment:CommonListFragment<Repository,RepositoryPresenter>() {
    override val adapter: CommonRecyclerAdapter<Repository>
        get() = RepositoryAdapter()
}