package com.tengfei.github.view.fragments

import android.os.Bundle
import com.tengfei.github.model.account.AccountManager
import com.tengfei.github.view.common.CommonViewPageFragment
import com.tengfei.github.view.common.FragmentPage

/**
 * @author tengfei
 * date 2019-09-01 17:58
 * email tengfeigo@outlook.com
 * description
 */
class RepositoryFragment : CommonViewPageFragment() {

    companion object{
        const val KEY_REPOSITORY_LIST_USER = "KEY_REPOSITORY_LIST_USER"
    }

    override fun getFragmentPageList(): List<FragmentPage> {
        return listOf(
                FragmentPage(RepositoryListFragment().apply { arguments = Bundle().apply { putParcelable(KEY_REPOSITORY_LIST_USER, AccountManager.currentUser) } }, "My"),
                FragmentPage(RepositoryListFragment(), "ALL")
        )
    }
}