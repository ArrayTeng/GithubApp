package com.tengfei.mvp.impl

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tengfei.mvp.IPresenter
import com.tengfei.mvp.IView
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author tengfei
 * date 2019/8/15 8:14 PM
 * email tengfeigo@outlook.com
 * description
 */
class BaseActivity<out P : BasePresenter<BaseActivity<P>>> : IView<P>, AppCompatActivity() {

    override val presenter: P

    init {
        presenter = createPresenterJava()
        presenter.view = this
    }

    private fun createPresenterJava(): P {
        sequence<Type> {
            var thisClass: Class<*> = this@BaseActivity.javaClass
            while (true) {
                @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                // genericSuperclass ：java 反射中 获得带有泛型的父类
                yield(thisClass.genericSuperclass)
                thisClass = thisClass.superclass ?: break
            }
        }.filter {
            it is ParameterizedType
        }.flatMap {
            (it as ParameterizedType).actualTypeArguments.asSequence()
        }.first {
            it is Class<*> && IPresenter::class.java.isAssignableFrom(it)
        }.let {
            @Suppress("UNCHECKED_CAST")
            return (it as Class<P>).newInstance()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        presenter.onViewStateRestored(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}