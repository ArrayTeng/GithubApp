package com.tengfei.mvp

/**
 * @author tengfei
 * date 2019/8/12 1:53 PM
 * email tengfeigo@outlook.com
 * description
 */

interface IView<out P : IPresenter<IView<P>>> : ILifecycle {
    val presenter: P
}

interface IPresenter<out V : IView<IPresenter<V>>> : ILifecycle {
    val view: V
}