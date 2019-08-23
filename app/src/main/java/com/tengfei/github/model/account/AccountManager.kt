package com.tengfei.github.model.account

import com.google.gson.Gson
import com.tengfei.common.ext.Preference
import com.tengfei.github.AppContext
import com.tengfei.github.entity.AccountEntitiesRequest
import com.tengfei.github.entity.AccountEntitiesResponse
import com.tengfei.github.entity.User
import com.tengfei.github.network.service.AuthService
import com.tengfei.github.network.service.UserService
import com.tengfei.github.utils.fromJson
import retrofit2.HttpException
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.Exception

/**
 * @author tengfei
 * date 2019/8/19 4:02 PM
 * email tengfeigo@outlook.com
 * description 用户管理类
 */

interface OnAccountStateChangeListener {
    fun onLogin(user: User)

    fun onLoginOut()
}

object AccountManager {

    var userName: String by Preference(AppContext, "userName_AccountManager", "")
    var passWord: String by Preference(AppContext, "passWord_AccountManager", "")
    var token: String by Preference(AppContext, "token_AccountManager", "")
    var authId: Int by Preference(AppContext, "authId_AccountManager", -1)

    val onAccountStateChangeListeners = ArrayList<OnAccountStateChangeListener>()

    private fun notifyLogin(user: User) {
        onAccountStateChangeListeners.forEach {
            it.onLogin(user)
        }
    }

    private fun notifyLoginOut() {
        onAccountStateChangeListeners.forEach {
            it.onLoginOut()
        }
    }

    /**
     * 保存用户
     */
    private var userJson: String by Preference(AppContext, "userJson_AccountManager", "")
    var currentUser: User? = null
        get() {
            if (field == null && userJson.isNotEmpty()) {
                field = Gson().fromJson<User>(userJson)
            }
            return field
        }
        set(value) {
            userJson = if (value == null) {
                ""
            } else {
                Gson().toJson(value)
            }
            field = value
        }

    fun login() = AuthService
            .createAuthorization(AccountEntitiesRequest())
            .doOnNext {
                //本地没有Token服务端又没有提供Token，只好在登录一次了
                if (it.token.isEmpty()) throw AccountException(it)
                //对于 retryWhen 如果 doOnNext 里抛出的异常是 AccountException 那么会执行 retryWhen
                //方法体里的代码然后重试也就是重新执行 createAuthorization
            }.retryWhen { it ->
                //因为传入的是 Observable 所以必须要先 flatMap 一下拿到值后在判断
                it.flatMap {
                    if (it is AccountException) {
                        AuthService.deleteAuthorization(it.accountEntitiesResponse.id)
                    } else {
                        Observable.error(it)
                    }
                }
            }.flatMap {
                //执行到这里的时候一定是有token的
                token = it.token
                //保存 authId 用于在将来登出操作的时候要用
                authId = it.id
                //登录已经完成了，继续执行获取鉴权user的步骤
                UserService.getAuthenticatedUser()
            }.map {
                currentUser = it
                notifyLogin(it)
            }
            //表示请求在 io 线程中使用
            //.subscribeOn(Schedulers.io())
            //最终结果在主线程中输出
            //.observeOn(AndroidSchedulers.mainThread())

    /**
     * 有 token 表示登录，没有 token 表示没有登录
     */
    fun isLogin(): Boolean = token.isNotEmpty()


    fun loginOut() = AuthService
            .deleteAuthorization(authId)
            .doOnNext {
                if (it.isSuccessful) {
                    token = ""
                    authId = -1
                    currentUser = null
                    notifyLoginOut()
                } else {
                    throw HttpException(it)
                }
            }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())


    class AccountException(val accountEntitiesResponse: AccountEntitiesResponse) : Exception("already login")
}