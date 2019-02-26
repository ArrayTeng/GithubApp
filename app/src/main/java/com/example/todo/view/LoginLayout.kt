package com.example.todo.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_login.view.*

/**
 * @author tengfei
 * date 2019/2/26 8:47 PM
 * email tengfeigo@outlook.com
 * description
 */
class LoginLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            val width = right - left
            val height = bottom - top
            //如果宽高比小于 3:4 的话说明输入框弹出布局被压缩
            if (width.toFloat() / height.toFloat() < 3F / 4F) {
                post {

                }
            } else {

            }
        }
    }
}