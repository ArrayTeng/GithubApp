package com.tengfei.github.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import org.jetbrains.anko.inputMethodManager

/**
 * @author tengfei
 * date 2019/8/21 6:38 PM
 * email tengfeigo@outlook.com
 * description
 */

fun Context.toggleSoftInput() {
    inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun View.showSoftInput(): Boolean {
    return context.inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun Activity.showSoftInput(): Boolean {
    return currentFocus?.showSoftInput()?: false
}

fun View.hideSoftInput(): Boolean {
    return context.inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun Activity.hideSoftInput(): Boolean {
    return currentFocus?.hideSoftInput()?: false
}

fun Context.isActive(): Boolean {
    return inputMethodManager.isActive
}