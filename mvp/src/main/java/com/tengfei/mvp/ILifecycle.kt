package com.tengfei.mvp

import android.content.res.Configuration
import android.os.Bundle

/**
 * @author tengfei
 * date 2019/8/12 2:03 PM
 * email tengfeigo@outlook.com
 * description
 */

interface ILifecycle {

    fun onCreate(savedInstanceState: Bundle?)

    fun onSaveInstanceState(outState: Bundle)

    fun onViewStateRestored(savedInstanceState: Bundle?)

    fun onConfigurationChanged(newConfig: Configuration)

    fun onDestroy()

    fun onStart()

    fun onStop()

    fun onResume()

    fun onPause()


}
