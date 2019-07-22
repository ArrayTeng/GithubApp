package com.example.todo.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.todo.utils.StatusBarUtil
import android.content.Intent
import android.widget.RelativeLayout
import com.example.todo.widget.titlebar.CommonTitleBar


/**
 * @author tengfei
 * date 2019/5/30 11:26 AM
 * email tengfeigo@outlook.com
 * description
 */
open class BaseActivity : AppCompatActivity() {

    private lateinit var mBaseLayout: RelativeLayout
    private lateinit var mTitleBar:CommonTitleBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setScreenPosition(true)
        setStatusBar()
        initTopView()
        initContent()
        initData()
    }

    private fun initData() {
    }

    private fun initContent() {
        setContentView(mBaseLayout)
    }

    private fun initTopView() {
        //默认情况下使用自定义的跟布局
        if (isUseCustomContent()){
            mBaseLayout = RelativeLayout(this)
            if (showTitleBar()){
               initTitleBar()
                mBaseLayout.addView(mTitleBar,0)
            }else{

            }
        }
    }

    private fun initTitleBar(){
        //初始化TitleBar
//        mTitleBar = CommonTitleBar(this)
//        mTitleBar.setLeftType(CommonTitleBar.TYPE_LEFT_TEXTVIEW,this)
//        mTitleBar.leftTextView.text = "fasfds"
    }

    /**
     * 是否显示标题栏目
     */
    open fun showTitleBar() = true

    /**
     * 跟布局是否使用自定义布局
     */
    open fun isUseCustomContent() = true

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