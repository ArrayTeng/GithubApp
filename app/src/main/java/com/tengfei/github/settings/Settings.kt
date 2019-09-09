package com.tengfei.github.settings

import android.annotation.SuppressLint
import com.tengfei.common.ext.Preference
import com.tengfei.github.AppContext
import com.tengfei.github.utils.pref

/**
 * @author tengfei
 * date 2019-09-09 21:00
 * email tengfeigo@outlook.com
 * description
 */
@SuppressLint("StaticFieldLeak")
object Settings {

    var themeModel by Preference(AppContext,"Key_ThemeModel","DAY")
}