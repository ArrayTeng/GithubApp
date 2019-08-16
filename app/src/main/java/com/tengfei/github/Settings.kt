package com.tengfei.github

import com.tengfei.common.ext.Preference

/**
 * @author tengfei
 * date 2019/8/9 10:58 AM
 * email tengfeigo@outlook.com
 * description
 */

object Settings {
    var email: String by Preference(AppContext, "email", "")
    var passWord: String by Preference(AppContext, "passWord", "")
}