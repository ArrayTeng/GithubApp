package com.example.todo

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import kotlinx.android.synthetic.main.activity_launcher.*

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
        setContentView(R.layout.activity_launcher)

        handler.postDelayed({
            goLoginRegister()
        }, 3000)

    }

    private fun goLoginRegister() {
        val location = IntArray(2)
        launcherImage.getLocationOnScreen(location)
        val intent = Intent(this@LauncherActivity, LoginRegisterActivity::class.java)
        intent.putExtra("X", location[0])
        intent.putExtra("Y", location[1])
        startActivity(intent)
        overridePendingTransition(0, 0)
        finish()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        handler.removeCallbacks(null)
        super.onDestroy()
    }


}