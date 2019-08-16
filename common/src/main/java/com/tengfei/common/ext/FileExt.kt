package com.tengfei.common.ext

import android.util.Log
import java.io.File

/**
 * @author tengfei
 * date 2019/8/16 10:10 PM
 * email tengfeigo@outlook.com
 * description
 */

private const val TAG = "FileExt"


fun File.ensureDir(): Boolean {
    try {
        isDirectory.no {
            isFile.yes {
                delete()
            }
            return mkdirs()
        }
    } catch (e: Exception) {
        Log.w(TAG, e.message)
    }
    return false
}