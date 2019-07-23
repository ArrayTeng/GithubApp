package com.example.todo

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.todo.utils.StatusBarUtil
import com.example.todo.utils.getScreenHeight
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
        image_login.setImageResource(R.mipmap.todo_logo)
        edit_login.visibility = View.GONE
        edit_register.visibility = View.GONE
        image_login.post {
            initLocation()
            initLogAnim()
        }


    }

    /**
     * 初始化以及执行动画
     */
    private fun initLogAnim() {

        //获取屏幕的高度
        val screenHeight = px2Dp(this, getScreenHeight(this).toFloat())
        //计算出 Log 的高度
        val statusBarHeight = px2Dp(this, StatusBarUtil.getStatusBarHeight(this).toFloat())
        val logTranslationY = (screenHeight / 2 - statusBarHeight) / 2
        val translationLogAnimator = ObjectAnimator.ofFloat(image_login, "translationY", image_login.y, logTranslationY)
        translationLogAnimator.duration = 3000
        translationLogAnimator.start()
        translationLogAnimator.addListener(object :Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                edit_login.visibility = View.VISIBLE
                edit_register.visibility = View.VISIBLE
                initBottomAnim()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
    }

    private fun initBottomAnim(){
        val translationLogin = ObjectAnimator.ofFloat(edit_login, "translationY", 200F, 0F)
        val translationRegister = ObjectAnimator.ofFloat(edit_register, "translationY", 200F, 0F)

        val alphaAnimationLogin = ObjectAnimator.ofFloat(edit_login, "alpha", 0F, 1F)
        val alphaAnimationRegister = ObjectAnimator.ofFloat(edit_register, "alpha", 0F, 1F)

        val bottomAnimatorSet = AnimatorSet()
        bottomAnimatorSet.duration = 3000
        bottomAnimatorSet.play(translationLogin).with(translationRegister).with(alphaAnimationLogin).with(alphaAnimationRegister)
        bottomAnimatorSet.start()
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