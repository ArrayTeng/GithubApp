package com.example.todo.utils

import android.content.Context
import android.graphics.drawable.Drawable


/**
 * @author tengfei
 * date 2019/7/10 12:01 AM
 * email tengfeigo@outlook.com
 * description UI相关的工具类
 */


fun dip2px(context: Context, dip: Int): Int {
    val density: Float = context.resources.displayMetrics.density
    val px = (dip * density + 0.5F) as Int
    return px
}

fun sp2px(context: Context, spValue: Float): Int {
    val fontScale: Float = context.resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5f) as Int
}

fun getColor(context: Context, colorId: Int): Int {
    return context.resources.getColor(colorId, null)
}

fun getDrawable(context: Context, resId: Int): Drawable {
    return context.resources.getDrawable(resId)
}




