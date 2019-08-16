package com.tengfei.github.utils

import com.tengfei.common.ext.Preference
import com.tengfei.github.AppContext
import kotlin.reflect.jvm.jvmName

/**
 * @author tengfei
 * date 2019/8/16 10:18 PM
 * email tengfeigo@outlook.com
 * description
 */

inline fun <reified R, T> R.pref(default: T) = Preference(AppContext, "", default, R::class.jvmName)