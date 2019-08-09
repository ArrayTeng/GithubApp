package com.tengfei.github

import android.app.Application
import android.content.ContextWrapper

/**
 * @author tengfei
 * date 2019/8/9 10:56 AM
 * email tengfeigo@outlook.com
 * description
 */
private lateinit var INSTANCE: Application

class GithubApp : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }


}

object AppContext : ContextWrapper(INSTANCE)