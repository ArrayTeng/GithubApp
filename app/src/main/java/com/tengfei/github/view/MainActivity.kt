package com.tengfei.github.view

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import com.tengfei.common.ext.otherWise
import com.tengfei.common.ext.yes
import com.tengfei.github.R
import com.tengfei.github.entity.User
import com.tengfei.github.model.account.AccountManager
import com.tengfei.github.utils.doOnLayoutAvailable
import com.tengfei.github.utils.loadWithGlide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(appBarMainToolbar)
        val toggle = ActionBarDrawerToggle(this, mainDrawerLayout, appBarMainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mainDrawerLayout.setDrawerListener(toggle)
        toggle.syncState()
    }

    private fun initNavigationView() {
        /**
         * Kotlin 中的let，当不为Null时执行let后面的代码块
         */
        AccountManager.currentUser?.let(::updateNavigationView) ?: run(::clearNavigationView)

        initNavigationViewEvent()
    }

    private fun initNavigationViewEvent() {
        // 这里就是使用Anko的好处，可以帮我们简化很多代码
        navigationView.doOnLayoutAvailable {
            navHeaderLayout.onClick {
                AccountManager.isLogin().yes {
                    AccountManager.loginOut().subscribe({
                        toast("注销账号")
                    },{
                        it.printStackTrace()
                    })
                }.otherWise {
                    //跳转到登陆界面
                }
            }
        }
    }

    private fun updateNavigationView(user: User) {
        navigationView.doOnLayoutAvailable {
            navHeaderUsernameView.text = user.name
            navHeaderEmailView.text = user.email ?: ""
            //使用 AvatarImageView 原因在于如果没有图片的话可以帮助我们显示一个字符充当图片
            navHeaderAvatarImageView.loadWithGlide(user.avatar_url, user.name.first())
        }
    }

    private fun clearNavigationView() {
        navigationView.doOnLayoutAvailable {
            navHeaderUsernameView.text = resources.getText(R.string.click_to_login)
            navHeaderEmailView.text = ""
            navHeaderAvatarImageView.imageResource = R.drawable.ic_github
        }
    }
}

