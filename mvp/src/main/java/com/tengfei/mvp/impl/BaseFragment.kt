package com.tengfei.mvp.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import com.tengfei.mvp.IPresenter
import com.tengfei.mvp.IView
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

/**
 * @author tengfei
 * date 2019/8/12 2:54 PM
 * email tengfeigo@outlook.com
 * description
 */
open class BaseFragment<out P : BasePresenter<BaseFragment<P>>> : IView<P>, Fragment() {
    override val presenter: P


    init {
        presenter = createPresenterJava()
        presenter.view = this
    }

    private fun createPresenterKt(): P {
        sequence {
            var thisClass: KClass<*> = this@BaseFragment::class
            while (true) {
                //supertypes 找到当前类型的直接父类基类列表
                yield(thisClass.supertypes)
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure ?: break
            }//实际上这里的 flatMap 操作的是 SequenceScope<List<KType>>
        }.flatMap { it ->
            it.flatMap {
                //获取父类里的泛型实参
                it.arguments
            }.asSequence()//转变成泛型实参的序列
        }.first {
            //如果是 IPresenter 的类型，那么执行到 let 里的代码
            it.type?.jvmErasure?.isSubclassOf(IPresenter::class) ?: false
        }.let {
            @Suppress("UNCHECKED_CAST")
            return it.type!!.jvmErasure.primaryConstructor!!.call() as P
        }
    }

    private fun createPresenterJava(): P {
        sequence<Type> {
            var thisClass: Class<*> = this@BaseFragment.javaClass
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        presenter.onViewStateRestored(savedInstanceState)
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