package com.tengfei.github.presenter

import android.util.Log
import com.tengfei.github.model.account.AccountManager
import com.tengfei.github.view.LoginActivity
import com.tengfei.mvp.impl.BasePresenter

/**
 * @author tengfei
 * date 2019/8/17 9:26 PM
 * email tengfeigo@outlook.com
 * description
 */
class LoginPresenter : BasePresenter<LoginActivity>() {

    fun doLogin(name: String, password: String) {
        AccountManager.userName = name
        AccountManager.passWord = password
        AccountManager.login().subscribe({
            Log.i("tmdXX","登录成功")
        }, {
            Log.i("tmdXX",it.message)
        })
    }
}