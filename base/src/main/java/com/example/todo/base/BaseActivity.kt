package com.example.todo.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.example.todo.utils.StatusBarUtil
import android.content.Intent


/**
 * @author tengfei
 * date 2019/5/30 11:26 AM
 * email tengfeigo@outlook.com
 * description
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setScreenPosition(true)
        setStatusBar()
    }

    /**
     * 设置屏幕旋转方向
     * @param screenPosition true为竖屏，false为横屏
     */
    open fun setScreenPosition(screenPosition: Boolean) {
        requestedOrientation = if (screenPosition) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    /**
     * 设置沉浸式实现
     */
    open fun setStatusBar() {
        StatusBarUtil.setColor(this, resources.getColor(android.R.color.white, null))
    }


    /**
     * 在Kotlin中使用 * 代表类型参数
     */
    open fun startActivity(clz: Class<*>) {
        startActivity(clz, null)
    }

    open fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent()
        intent.setClass(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }


    open fun startActivityForResult(cls: Class<*>, bundle: Bundle?, requestCode: Int) {
        val intent = Intent()
        intent.setClass(this, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }
}