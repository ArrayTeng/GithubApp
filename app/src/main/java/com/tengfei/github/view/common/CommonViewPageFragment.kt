package com.tengfei.github.view.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tengfei.common.ext.otherWise
import com.tengfei.common.ext.yes
import com.tengfei.github.R
import com.tengfei.github.entity.User
import com.tengfei.github.model.account.AccountManager
import com.tengfei.github.model.account.OnAccountStateChangeListener
import com.tengfei.github.view.MainActivity
import kotlinx.android.synthetic.main.fragment_viewpage_common.*

/**
 * @author tengfei
 * date 2019-09-01 16:02
 * email tengfeigo@outlook.com
 * description
 */


abstract class CommonViewPageFragment : Fragment(), IGetFragmentPageList, OnAccountStateChangeListener {

    private val fragmentPagerAdapter by lazy {
        CommonViewPageAdapter(childFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AccountManager.onAccountStateChangeListeners.add(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_viewpage_common, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).actionBarController.setUpWithViewPager(commonViewPage)
        commonViewPage.adapter = fragmentPagerAdapter
        fragmentPagerAdapter.fragmentPages.addAll(
                AccountManager.isLogin().yes {
                    getFragmentPageListLogin()
                }.otherWise {
                    getFragmentPageListNoLogin()
                }
        )

    }

//    override fun onResume() {
//        super.onResume()
//        if(fragmentPagerAdapter.count<=1){
//            (activity as MainActivity).actionBarController.tableLayout.visibility = View.GONE
//        }else{
//            (activity as MainActivity).actionBarController.tableLayout.visibility = View.VISIBLE
//        }
//    }

    override fun onLogin(user: User) {
        fragmentPagerAdapter.fragmentPages.clear()
        fragmentPagerAdapter.fragmentPages.addAll(getFragmentPageListLogin())
//        if(fragmentPagerAdapter.count<=1){
//            (activity as MainActivity).actionBarController.tableLayout.visibility = View.GONE
//        }else{
//            (activity as MainActivity).actionBarController.tableLayout.visibility = View.VISIBLE
//        }
    }

    override fun onLoginOut() {
        fragmentPagerAdapter.fragmentPages.clear()
        fragmentPagerAdapter.fragmentPages.addAll(getFragmentPageListNoLogin())
//        if(fragmentPagerAdapter.count<=1){
//            (activity as MainActivity).actionBarController.tableLayout.visibility = View.GONE
//        }else{
//            (activity as MainActivity).actionBarController.tableLayout.visibility = View.VISIBLE
//        }
    }

    override fun onDestroy() {
        AccountManager.onAccountStateChangeListeners.remove(this)
        super.onDestroy()
    }
}