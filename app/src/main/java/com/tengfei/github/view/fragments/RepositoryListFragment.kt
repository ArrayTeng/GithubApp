package com.tengfei.github.view.fragments

import com.tengfei.github.entity.Repository
import com.tengfei.github.entity.User
import com.tengfei.github.presenter.RepositoryPresenter
import com.tengfei.github.view.common.CommonListFragment

/**
 * @author tengfei
 * date 2019-08-29 17:48
 * email tengfeigo@outlook.com
 * description
 */


class RepositoryListFragment : CommonListFragment<Repository, RepositoryPresenter>() {

    override val adapter by lazy {
        RepositoryAdapter()
    }


}