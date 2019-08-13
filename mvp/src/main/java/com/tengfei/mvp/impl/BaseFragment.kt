package com.tengfei.mvp.impl

import android.support.v4.app.Fragment
import com.tengfei.mvp.IView

/**
 * @author tengfei
 * date 2019/8/12 2:54 PM
 * email tengfeigo@outlook.com
 * description
 */
class BaseFragment<out P : BasePresenter<BaseFragment<P>>> : IView<P>, Fragment() {
    override val presenter: P


    init {
        presenter = createPresenter()
        presenter.view = this
    }

    private fun createPresenter(): P = TODO()

}