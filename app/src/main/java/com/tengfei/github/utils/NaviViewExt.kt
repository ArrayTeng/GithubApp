package com.tengfei.github.utils

import android.annotation.SuppressLint
import android.support.annotation.IdRes
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.view.menu.MenuItemImpl
import android.view.View
import com.tengfei.common.ext.otherWise
import com.tengfei.common.ext.yes
import com.tengfei.common.log.logger
import com.tengfei.github.view.config.NavViewItem
import android.support.v4.widget.DrawerLayout.DrawerListener


/**
 * @author tengfei
 * date 2019-08-22 15:07
 * email tengfeigo@outlook.com
 * description
 */

inline fun NavigationView.doOnLayoutAvailable(crossinline block: () -> Unit) {
    /**
     * 只有当 View 至少经历过一次 layout 时，isLaidOut() 方法才能返回 true
     */
    ViewCompat.isLaidOut(this).yes {
        block()
    }.otherWise {
        addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                removeOnLayoutChangeListener(this)
                block()
            }
        })
    }
}


/**
 * 选择指定的菜单，并执行相应的操作
 */
//@SuppressLint("RestrictedApi")
//fun NavigationView.selectItem(@IdRes resId: Int){
//    doOnLayoutAvailable {
//        logger.debug("select Item: ${NavViewItem[resId].title}")
//        setCheckedItem(resId)
//        (menu.findItem(resId) as MenuItemImpl)()
//    }
//}

inline fun DrawerLayout.afterClosed(crossinline block: () -> Unit){
    if(isDrawerOpen(GravityCompat.START)) {
        closeDrawer(GravityCompat.START)
        addDrawerListener(
                object : DrawerListener {
                    override fun onDrawerStateChanged(newState: Int) = Unit
                    override fun onDrawerSlide(drawerView: View, slideOffset: Float) = Unit
                    override fun onDrawerOpened(drawerView: View) = Unit

                    override fun onDrawerClosed(drawerView: View) {
                        removeDrawerListener(this)
                        block()
                    }
                })
    } else {
        block()
    }
}
