package com.tengfei.github.settings

import android.app.Activity
import android.support.annotation.StyleRes
import com.tengfei.github.R

/**
 * @author tengfei
 * date 2019-09-09 15:53
 * email tengfeigo@outlook.com
 * description
 */
object Themer {

    enum class ThemeModel(@StyleRes val theme: Int) {
        DAY(R.style.AppTheme_DayTimePattern), NIGHT(R.style.AppTheme_NightTimePattern)
    }

    fun currentTheme() = ThemeModel.valueOf(Settings.themeModel)

    fun applyTheme(activity: Activity) {
        activity.setTheme(currentTheme().theme)
    }

    /**
     * 切换主题
     */
    fun toggleTheme(activity: Activity) {
        when (currentTheme()) {
            Themer.ThemeModel.DAY -> Settings.themeModel = Themer.ThemeModel.NIGHT.name
            Themer.ThemeModel.NIGHT -> Settings.themeModel = Themer.ThemeModel.DAY.name
        }
        activity.recreate()
    }

}