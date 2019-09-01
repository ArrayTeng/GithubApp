package com.tengfei.github.view.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tengfei.github.R
import com.tengfei.github.view.MainActivity
import kotlinx.android.synthetic.main.fragment_viewpage_common.*

/**
 * @author tengfei
 * date 2019-09-01 16:02
 * email tengfeigo@outlook.com
 * description
 */


abstract class CommonViewPageFragment : Fragment(), IGetFragmentPageList {

    private val fragmentPagerAdapter by lazy {
        CommonViewPageAdapter(childFragmentManager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_viewpage_common, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).actionBarController.setUpWithViewPager(commonViewPage)
        commonViewPage.adapter = fragmentPagerAdapter
        fragmentPagerAdapter.fragmentPages.addAll(getFragmentPageList())
    }


}