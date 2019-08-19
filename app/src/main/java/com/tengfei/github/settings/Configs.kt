package com.tengfei.github.settings

import com.tengfei.common.log.logger
import com.tengfei.github.AppContext
import com.tengfei.github.utils.deviceId

/**
 * @author tengfei
 * date 2019/8/19 1:37 PM
 * email tengfeigo@outlook.com
 * description
 */
object Configs {

    object Account{
        val SCOPES:List<String> = listOf("user", "repo", "notifications", "gist", "admin:org")
        const val clientId = "81dfd3a85c55ce3f85f0"
        const val clientSecret = "6f1bb9c1f1f4f5c590052f1ce666dae1f75d19ef"
        const val note = "tengfei.cn"
        const val noteUrl = "https://github.com/TengfeiGo"

        val fingerPrint by lazy {
            (AppContext.deviceId + clientId).also { logger.info("fingerPrint: "+it) }
        }

    }


}