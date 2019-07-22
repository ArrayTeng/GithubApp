package com.example.todo

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
        image_login.setImageResource(R.mipmap.todo_logo)
        image_login.post{
            initLocation()
        }
        //initAnim()


    }

    /**
     * 初始化以及执行动画
     */
    private fun initAnim() {
        val translationLogin = ObjectAnimator.ofFloat(tv_login, "translationY",200F,0F)
        translationLogin.start()
    }

    private fun initLocation() {
        val translateY = getTranslateY()
        //放到前一个页面的位置
        image_login.y = translateY
    }

    private fun getTranslateY(): Float {
        val originY = intent.getIntExtra("Y", 0).toFloat()
        val location = IntArray(2)
        // getLocationOnScreen 函数的作用在于获取 View 对象左上角相对于屏幕的绝对位置
        image_login.getLocationOnScreen(location)
        return originY - location[1].toFloat()
    }


}