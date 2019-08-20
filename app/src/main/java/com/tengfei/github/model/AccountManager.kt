package com.tengfei.github.model

import com.tengfei.common.ext.Preference
import com.tengfei.github.AppContext
import com.tengfei.github.entity.AccountEntitiesRequest
import com.tengfei.github.network.service.AuthService
import com.tengfei.github.utils.pref
import org.jetbrains.anko.Android
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author tengfei
 * date 2019/8/19 4:02 PM
 * email tengfeigo@outlook.com
 * description 用户管理类
 */
object AccountManager {

    var userName: String by Preference(AppContext, "userName_AccountManager", "")
    var passWord: String by Preference(AppContext, "passWord_AccountManager", "")
    var token: String by Preference(AppContext, "token_AccountManager", "")

    fun isLogin(): Boolean = TODO()

    fun login() {
        AuthService.createAuthorization(AccountEntitiesRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {

                }

    }
}