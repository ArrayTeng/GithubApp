package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.todo.base.BaseActivity

/**
 * @author tengfei
 * date 2019/7/21 11:14 PM
 * email tengfeigo@outlook.com
 * description
 */
class LauncherActivity : AppCompatActivity() {

    private val handler: Handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handler.postDelayed({
            val intent = Intent(this@LauncherActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }


}