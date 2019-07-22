package com.example.todo.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * @author tengfei
 * date 2019/7/10 12:01 AM
 * email tengfeigo@outlook.com
 * description UI相关的工具类
 */


fun density(context: Context): Float {
    return context.resources.displayMetrics.density
}

fun dip2px(context: Context, dip: Int): Int {
    val density: Float = context.resources.displayMetrics.density
    return (dip * density + 0.5F).toInt()
}

fun dp2PxInt(context: Context, dp: Float): Int {
    return (dip2px(context, dp.toInt()) + 0.5f).toInt()
}

fun px2Dp(context: Context?, px: Float): Float {
    return if (context == null) {
        -1f
    } else px / density(context)
}


fun sp2px(context: Context, spValue: Float): Int {
    val fontScale: Float = context.resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5f).toInt()
}


fun getColor(context: Context, colorId: Int): Int {
    return context.resources.getColor(colorId, null)
}

fun getDrawable(context: Context, resId: Int): Drawable {
    return context.resources.getDrawable(resId, null)
}

/**
 * 显示软键盘
 */
fun showSoftInputKeyBoard(context: Context, focusView: View) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(focusView, InputMethodManager.SHOW_FORCED)
}

/**
 * 隐藏软键盘
 */
fun hideSoftInputKeyBoard(context: Context, focusView: View?) {
    if (focusView != null) {
        val binder = focusView.windowToken
        if (binder != null) {
            val imd = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imd.hideSoftInputFromWindow(binder, InputMethodManager.HIDE_IMPLICIT_ONLY)
        }
    }
}

/**
 * 获取屏幕大小
 *
 * @param context
 * @return
 */
fun getScreenPixelSize(context: Context): IntArray {
    val metrics = getDisplayMetrics(context)
    return intArrayOf(metrics.widthPixels, metrics.heightPixels)
}

fun getDisplayMetrics(context: Context): DisplayMetrics {
    val activity: Activity
    if (context !is Activity && context is ContextWrapper) {
        activity = context.baseContext as Activity
    } else {
        activity = context as Activity
    }
    val metrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(metrics)
    return metrics
}






