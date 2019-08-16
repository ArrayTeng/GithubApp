package com.tengfei.common

import com.tengfei.common.ext.no
import com.tengfei.common.ext.otherWise
import org.junit.Test

/**
 * @author tengfei
 * date 2019/8/8 2:38 PM
 * email tengfeigo@outlook.com
 * description
 */
class ExampleTest {
    @Test
    internal fun testBoolean() {
        val result = getBoolean().no {
            1
        }.otherWise {
           2
        }

        print(result)
    }

    fun getBoolean():Boolean = false


}