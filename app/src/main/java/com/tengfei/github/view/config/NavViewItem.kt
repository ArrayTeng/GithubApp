package com.tengfei.github.view.config

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import com.tengfei.github.R.id
import com.tengfei.github.R.drawable
import com.tengfei.github.view.fragments.AboutFragment

/**
 * @author tengfei
 * date 2019-08-22 15:09
 * email tengfeigo@outlook.com
 * description
 */

class NavViewItem private constructor(val groupId: Int = 0, val title: String, @DrawableRes val icon: Int, val fragmentClass: Class<out Fragment>, val arguements: Bundle = Bundle()){

    companion object{
        private val items = mapOf(
                id.repository to NavViewItem(0, "Issue",drawable.ic_repository, AboutFragment::class.java)
        )

         fun get(@IdRes navId: Int): NavViewItem {
            return items[navId]?: items[id.repository]!!
        }

         fun get(item: NavViewItem): Int{
            return items.filter { it.value == item }.keys.first()
        }
    }

    override fun toString(): String {
        return "NavViewItem(groupId=$groupId, title='$title', icon=$icon, fragmentClass=$fragmentClass, arguements=$arguements)"
    }
}
