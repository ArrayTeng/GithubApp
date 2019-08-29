package com.tengfei.github.utils

import kotlin.math.roundToInt

/**
 * @author tengfei
 * date 2019-08-29 22:33
 * email tengfeigo@outlook.com
 * description
 */

fun Int.toKilo(): String {
    return if (this >= 700) {
        "${(this / 100F).roundToInt() / 10F}K"
    } else {
        "$this"
    }
}