package com.tengfei.github.view

import android.os.Bundle
import android.widget.EditText
import com.tengfei.github.R
import com.tengfei.github.presenter.LoginPresenter
import com.tengfei.github.view.widget.ObjectPropertyDelegate
import com.tengfei.mvp.impl.BaseActivity

import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity<LoginPresenter>() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        emailSignInButton.setOnClickListener {
            presenter.doLogin("tengfeigo@outlook.com","TengFei1995")
        }
    }


}
