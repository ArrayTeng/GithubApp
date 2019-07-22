package com.example.todo

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.example.todo.utils.StatusBarUtil
import com.example.todo.utils.px2Dp
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @author tengfei
 * date 2019/7/22 8:15 PM
 * email tengfeigo@outlook.com
 * description
 */
class LoginRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //StatusBarUtil.setColor(this,resources.getColor(android.R.color.white,null))
        imageLogin.setImageResource(R.mipmap.todo_logo)
        imageLogin.post{
            initLocation()
        }


    }

    private fun initLocation() {
        val translateY = getTranslateY()
        //放到前一个页面的位置
        imageLogin.y = translateY
    }

    private fun getTranslateY(): Float {
        val originY = intent.getIntExtra("Y", 0).toFloat()
        val location = IntArray(2)
        imageLogin.getLocationOnScreen(location)
        return originY - location[1].toFloat()
    }


}