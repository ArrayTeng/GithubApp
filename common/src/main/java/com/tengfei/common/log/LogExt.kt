package com.tengfei.common.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory


/**
 * @author tengfei
 * date 2019/8/16 10:11 PM
 * email tengfeigo@outlook.com
 * description
 */

val loggerMap = HashMap<Class<*>, Logger>()

inline val <reified T> T.logger: Logger
    get() {
        return loggerMap[T::class.java]?: LoggerFactory.getLogger(T::class.java).apply { loggerMap[T::class.java] = this }
    }