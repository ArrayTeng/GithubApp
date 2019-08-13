package com.tengfei.common.test

import android.app.backup.BackupAgent
import java.io.BufferedReader
import java.io.FileReader


/**
 * @author tengfei
 * date 2019/8/11 2:17 PM
 * email tengfeigo@outlook.com
 * description
 */

fun main() {

    BufferedReader(FileReader("hello.text")).use {

    }
}

data class Person(var name: String, var age: String){
    fun work(){}
}






