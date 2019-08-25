package com.tengfei.github.utils

/**
 * @author tengfei
 * date 2019-08-25 20:39
 * email tengfeigo@outlook.com
 * description
 */


import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by benny on 9/25/17.
 */

/**
 * ISO 8601
 * https://stackoverflow.com/questions/19112357/java-simpledateformatyyyy-mm-ddthhmmssz-gives-timezone-as-ist
 */
val githubDateFormatter by lazy {
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.CHINA)
            .apply {
                timeZone = TimeZone.getTimeZone("GMT")
            }
}

val outputDateFormatter by lazy {
    SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA)
}

fun Date.format(pattern: String) = SimpleDateFormat(pattern, Locale.CHINA).format(this)

fun githubTimeToDate(time: String) = githubDateFormatter.parse(time)

fun Date.view(): String{
    val now = System.currentTimeMillis()
    val seconds = (now - this.time) / 1000
    val minutes = seconds / 60
    return if(minutes >= 60){
        val hours = minutes / 60
        if(hours in 24..47){
            "Yesterday"
        } else if(hours >= 48 ){
            outputDateFormatter.format(this)
        } else {
            "$hours hours ago"
        }
    } else if(minutes < 1){
        "$seconds seconds ago"
    } else {
        "$minutes minutes ago"
    }
}