package com.tengfei.common.test

import android.util.Log.v
import java.io.BufferedReader
import java.io.FileReader
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor


/**
 * @author tengfei
 * date 2019/8/11 2:17 PM
 * email tengfeigo@outlook.com
 * description 注意 noArgs
 */

data class People(val name: String, var age: String) {
    fun hello01() {
        println("hello01")
    }

    fun hello01(nameString: String) {
        println(nameString)
    }
}

class NoPrimaryConstructor {

    constructor()

    constructor(i: Int)

    constructor(i: Int, name: String)

}

fun main() {

//    // 1 Kotlin中使用Kotlin反射获取class对象的三种方式
//    val people = People("feifei","23")
//    val kClazz01:KClass<People> = People::class
//    val kClazz02:KClass<out People> = people::class
//    val kClazz03:KClass<People> = people.javaClass.kotlin
//
//    // 2 通过Kotlin反射构造对象
//    // 2.1 通过主构造器来构造对象
//    val kPrimaryConstructor:KFunction<People>? = kClazz01.primaryConstructor
//    val kPeople = kPrimaryConstructor!!.call("kuku","25")
//    print(kPeople)

    // 3 没有主构造器只有从构造器的时候
//    val clazz = NoPrimaryConstructor::class
//    val noPrimaryConstructor01 = clazz.constructors.toMutableList()[0].call()
//    val noPrimaryConstructor02 = clazz.constructors.toMutableList()[1].call(1)
//    print(noPrimaryConstructor02)

    // 4 在Kotlin中通过反射获取属性
//    val kClazz: KClass<People> = People::class
//    val people = kClazz.primaryConstructor!!.call("feifei","23")
//    //获取People里属性为 name 的值
//    (kClazz.memberProperties.first { it.name == "age" } as? KMutableProperty1<People,String>)?.set(people,"44")
//    print(people)
//
    // 5 Kotlin中通过反射获取方法
    val kClazz: KClass<People> = People::class
    val people = kClazz.primaryConstructor!!.call("feifei", "23")
    val method = kClazz.memberFunctions.toMutableList()[0].call()
    print(method)
}






