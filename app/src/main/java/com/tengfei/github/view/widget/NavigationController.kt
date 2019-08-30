package com.tengfei.github.view.widget

import android.support.design.widget.NavigationView
import android.view.MenuItem
import com.tengfei.github.R
import com.tengfei.github.utils.selectItem
import com.tengfei.github.view.config.NavViewItem

/**
 * @author tengfei
 * date 2019-08-30 16:48
 * email tengfeigo@outlook.com
 * description
 */
class NavigationController constructor(private val navigationView: NavigationView, private val onNavItemClick: (NavViewItem) -> Unit) : NavigationView.OnNavigationItemSelectedListener {

    init {
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navViewItem = NavViewItem.getItem(item.itemId)
        onNavItemClick(navViewItem)
        return true
    }

    fun selectProperItem() {
        navigationView.selectItem(R.id.repository)
    }
}