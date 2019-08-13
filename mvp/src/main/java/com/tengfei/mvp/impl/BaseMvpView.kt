package com.tengfei.mvp.impl

import com.tengfei.mvp.IPresenter
import com.tengfei.mvp.IView

/**
 * @author tengfei
 * date 2019/8/12 2:06 PM
 * email tengfeigo@outlook.com
 * description
 */
abstract class BaseMvpView<out P : IPresenter<BaseMvpView<P>>> : IView<P> {

}