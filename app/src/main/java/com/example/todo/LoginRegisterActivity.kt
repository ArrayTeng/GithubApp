package com.example.todo

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.todo.utils.px2Dp
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @author tengfei
 * date 2019/7/22 8:15 PM
 * email tengfeigo@outlook.com
 * description 登录注册界面
 */
class LoginRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_body.visibility = View.GONE
        drawable_text_login.post {
            initLocation()
            initLogAnim()
        }

    }

    /**
     * 初始化以及执行动画
     */
    private fun initLogAnim() {

        val translationLogAnimator = ObjectAnimator.ofFloat(drawable_text_login, "translationY", drawable_text_login.translationY, px2Dp(this,drawable_text_login.top.toFloat()))
        translationLogAnimator.duration = 3000
        translationLogAnimator.start()
        translationLogAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                login_body.visibility = View.VISIBLE
                initBottomAnim()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
    }

    private fun initBottomAnim() {
        val translationBody = ObjectAnimator.ofFloat(login_body, "translationY", drawable_text_login.translationY, px2Dp(this,drawable_text_login.top.toFloat()))

        val alphaAnimationBody = ObjectAnimator.ofFloat(login_body, "alpha", 0F, 1F)

        val bottomAnimatorSet = AnimatorSet()
        bottomAnimatorSet.duration = 3000
        bottomAnimatorSet.play(translationBody).with(alphaAnimationBody)
        bottomAnimatorSet.start()
    }

    private fun initLocation() {
        val translateY = getTranslateY()
        //放到前一个页面的位置
        drawable_text_login.y = drawable_text_login.y + translateY.toFloat()
    }

    private fun getTranslateY(): Int {
        val originY = intent.getIntExtra("Y", 0)
        val location = IntArray(2)
        // getLocationOnScreen 函数的作用在于获取 View 对象左上角相对于屏幕的绝对位置
        drawable_text_login.getLocationOnScreen(location)
        return originY - location[1]
    }


}