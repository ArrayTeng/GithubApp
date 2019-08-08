package com.tengfei.common

/**
 * @author tengfei
 * date 2019/8/8 2:30 PM
 * email tengfeigo@outlook.com
 * description
 */

sealed class BooleanExt<out T>

object Otherwise : BooleanExt<Nothing>()

class WithData<T>(val data: T) : BooleanExt<T>()

inline fun <T> Boolean.yes(block: () -> T): BooleanExt<T> = when {
    this -> {

        WithData(block())
    }
    else -> {
        Otherwise
    }
}

inline fun <T> Boolean.no(block: () -> T): BooleanExt<T> = when {
    this -> {
        Otherwise
    }
    else -> {
        WithData(block())
    }
}

inline fun <T> BooleanExt<T>.otherWise(block: () -> T): T = when (this) {
    is Otherwise -> {
        block()
    }
    is WithData -> this.data
}
