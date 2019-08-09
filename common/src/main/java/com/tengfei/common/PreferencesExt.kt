package com.tengfei.common

import android.annotation.SuppressLint
import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author tengfei
 * date 2019/8/8 10:27 PM
 * email tengfeigo@outlook.com
 * description
 */

class Preference<T>(private val context: Context, private val name: String, private val default: T, private val preferenceName: String = "default") : ReadWriteProperty<Any?, T> {

    private val preferences by lazy {
        context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findValue(name)
    }

    @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
    private fun findValue(key: String): T {
        return when (default) {
            is String -> preferences.getString(key, default)
            is Int -> preferences.getInt(key, default)
            is Boolean -> preferences.getBoolean(key, default)
            is Long -> preferences.getLong(key, default)
            is Float -> preferences.getFloat(key, default)
            else -> throw IllegalAccessException("不支持的数据类型")
        } as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    @SuppressLint("CommitPrefEdits")
    private fun putPreference(key: String, value: T) {
        with(preferences.edit()) {
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                else -> throw IllegalAccessException("不支持的数据类型")
            }
        }.commit()
    }

}