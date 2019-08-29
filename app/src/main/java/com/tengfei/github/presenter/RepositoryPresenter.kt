package com.tengfei.github.presenter

import com.tengfei.github.entity.Repository
import com.tengfei.github.model.account.AccountManager
import com.tengfei.github.model.page.ListPage
import com.tengfei.github.model.repo.RePoDataList
import com.tengfei.github.view.common.CommonListPresenter
import com.tengfei.github.view.fragments.RepositoryFragment

/**
 * @author tengfei
 * date 2019-08-29 17:52
 * email tengfeigo@outlook.com
 * description
 */
class RepositoryPresenter: CommonListPresenter<Repository, RepositoryFragment>() {
    override val listPage: ListPage<Repository>
        get() {
            return RePoDataList(AccountManager.currentUser)
        }
}