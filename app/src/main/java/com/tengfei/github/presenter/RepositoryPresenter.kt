package com.tengfei.github.presenter

import com.tengfei.github.entity.Repository
import com.tengfei.github.entity.User
import com.tengfei.github.model.repo.RePoDataList
import com.tengfei.github.view.common.CommonListPresenter
import com.tengfei.github.view.fragments.RepositoryFragment.Companion.KEY_REPOSITORY_LIST_USER
import com.tengfei.github.view.fragments.RepositoryListFragment

/**
 * @author tengfei
 * date 2019-08-29 17:52
 * email tengfeigo@outlook.com
 * description
 */
class RepositoryPresenter : CommonListPresenter<Repository, RepositoryListFragment>() {

    private  var currentUser: User? = null

    override val listPage by lazy {
        val userArguments = view.arguments
        if (userArguments != null) {
            currentUser = userArguments.getParcelable(KEY_REPOSITORY_LIST_USER)

        }
        RePoDataList(currentUser)


    }


}