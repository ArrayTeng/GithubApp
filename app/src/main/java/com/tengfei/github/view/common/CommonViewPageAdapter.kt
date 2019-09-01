package com.tengfei.github.view.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.tengfei.github.utils.FragmentPageList

/**
 * @author tengfei
 * date 2019-09-01 16:02
 * email tengfeigo@outlook.com
 * description
 */

data class FragmentPage(val fragment: Fragment, val title: String)

interface IGetFragmentPageList {

    fun getFragmentPageList(): List<FragmentPage>
}


class CommonViewPageAdapter(private val fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {


    val fragmentPages = FragmentPageList<FragmentPage>(this)

    override fun getItem(position: Int): Fragment {
        return fragmentPages[position].fragment
    }

    override fun getCount(): Int {
        return fragmentPages.size
    }

    override fun getItemPosition(fragment: Any): Int {
        for ((index, page) in fragmentPages.withIndex()) {
            if (fragment == page.fragment) {
                return index
            }
        }
        return POSITION_NONE
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentPages[position].title
    }
}