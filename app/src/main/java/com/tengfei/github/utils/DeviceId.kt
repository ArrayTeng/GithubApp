package com.tengfei.github.utils

import android.content.Context
import android.provider.Settings

/**
 * @author tengfei
 * date 2019/8/16 10:16 PM
 * email tengfeigo@outlook.com
 * description
 */

val Context.deviceId: String
    get() = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID
    )