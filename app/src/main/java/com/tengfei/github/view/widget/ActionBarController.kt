package com.tengfei.github.view.widget

import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.tengfei.github.view.MainActivity
import com.tengfei.github.view.common.CommonViewPageAdapter
import com.tengfei.github.view.common.FragmentPagerAdapter
import kotlinx.android.synthetic.main.app_bar_main.*

/**
 * @author tengfei
 * date 2019-09-01 15:57
 * email tengfeigo@outlook.com
 * description
 */
class ActionBarController(val activity: MainActivity) {

     val tableLayout: TabLayout by lazy {
        activity.mainTabLayout
    }



    fun setUpWithViewPager(viewPager: ViewPager?) {

        tableLayout.setupWithViewPager(viewPager)
    }

    fun checkBarVisible(adapter: FragmentPagerAdapter){
        if(adapter.count<=1){
            (activity as MainActivity).actionBarController.tableLayout.visibility = View.GONE
        }else{
            (activity as MainActivity).actionBarController.tableLayout.visibility = View.VISIBLE
        }

    }



}