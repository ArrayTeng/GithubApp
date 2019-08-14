package com.tengfei.mvp.impl

import android.support.v4.app.Fragment
import com.tengfei.mvp.IPresenter
import com.tengfei.mvp.IView
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
class BaseFragment<out P : BasePresenter<BaseFragment<P>>> : IView<P>, Fragment() {
    override val presenter: P


    init {
        presenter = createPresenter()
        presenter.view = this
    }

    private fun createPresenter(): P {
        sequence {
            var thisClass: KClass<*> = this@BaseFragment::class
            while (true) {
                //通过遍历当前类型的所有父类
                yield(thisClass.supertypes)
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure ?: break
            }
        }.flatMap { it ->
            it.flatMap {
                it.arguments
            }.asSequence()
        }.first {
            it.type?.jvmErasure?.isSubclassOf(IPresenter::class) ?: false
        }.let {
            @Suppress("UNCHECKED_CAST")
            return it.type!!.jvmErasure.primaryConstructor!!.call() as P
        }
    }

}