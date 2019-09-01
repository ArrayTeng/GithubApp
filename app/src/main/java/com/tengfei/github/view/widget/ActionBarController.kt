package com.tengfei.github.view.widget

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.tengfei.github.view.MainActivity
import kotlinx.android.synthetic.main.app_bar_main.*

/**
 * @author tengfei
 * date 2019-09-01 15:57
 * email tengfeigo@outlook.com
 * description
 */
class ActionBarController(val activity: MainActivity) {

    private val tableLayout: TabLayout by lazy {
        activity.mainTabLayout
    }


    fun setUpWithViewPager(viewPager: ViewPager?) {
        tableLayout.setupWithViewPager(viewPager)
    }

}