package com.tengfei.common.test

import android.util.Log.v
import java.io.BufferedReader
import java.io.FileReader
import java.lang.Thread.yield
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure


/**
 * @author tengfei
 * date 2019/8/11 2:17 PM
 * email tengfeigo@outlook.com
 * description 注意 noArgs
 */


fun main() {
    test()

}

open class Peopel {}

open class Peopel02 : Peopel() {}

open class Peopel03 : Peopel02() {}

open class Peopel04 : Peopel03() {}


fun test() {
   sequence {
        var thisClazz: KClass<*> = Peopel04::class
        while (true) {
            println("执行到 sequence ${thisClazz.supertypes}")
            yield(thisClazz.supertypes)
            thisClazz = thisClazz.supertypes.firstOrNull()?.jvmErasure ?: break
        }
    }.flatMap{ it ->
       it.flatMap{
           it.arguments
       }.asSequence()
   }


}






