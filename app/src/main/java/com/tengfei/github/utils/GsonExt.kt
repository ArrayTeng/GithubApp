package com.tengfei.github.utils

import com.google.gson.Gson

/**
 * @author tengfei
 * date 2019/8/16 10:16 PM
 * email tengfeigo@outlook.com
 * description
 */
inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)