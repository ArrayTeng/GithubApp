package com.example.todo

import android.os.Bundle
import com.example.todo.base.BaseActivity
import com.example.todo.utils.StatusBarUtil

/**
 * 主界面
 *
 */
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarUtil.setColor(this, resources.getColor(R.color.colorPrimary, null))



    }

}
