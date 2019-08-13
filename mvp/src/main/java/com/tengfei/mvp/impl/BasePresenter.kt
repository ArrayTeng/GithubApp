package com.tengfei.mvp.impl

import android.content.res.Configuration
import android.os.Bundle
import com.tengfei.mvp.IPresenter
import com.tengfei.mvp.IView

/**
 * @author tengfei
 * date 2019/8/12 2:04 PM
 * email tengfeigo@outlook.com
 * description
 */
abstract class BasePresenter<out V : IView<BasePresenter<V>>> : IPresenter<V> {

    override lateinit var view: @UnsafeVariance V

    override fun onCreate(savedInstanceState: Bundle?) = Unit

    override fun onSaveInstanceState(outState: Bundle) = Unit

    override fun onViewStateRestored(savedInstanceState: Bundle?) = Unit

    override fun onConfigurationChanged(newConfig: Configuration) = Unit

    override fun onDestroy() = Unit

    override fun onStart() = Unit

    override fun onStop() = Unit

    override fun onResume() = Unit

    override fun onPause() = Unit
}