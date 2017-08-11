package com.davis.ktprogram.util


import com.davis.ktprogram.BuildConfig

/**
 * the logger

 * @author MaTianyu 2014-1-1下午4:05:39
 */
object LogUtils {

    /**
     * isPrint: print switch, true will print. false not print
     */
    var isPrint = BuildConfig.DEBUG

    /**
     * ******************** Log **************************
     */
    fun v(tag: String, msg: String?): Int {
        return if (isPrint && msg != null) android.util.Log.v(tag, msg) else -1
    }

    fun d(tag: String, msg: String?): Int {
        return if (isPrint && msg != null) android.util.Log.d(tag, msg) else -1
    }

    fun i(tag: String, msg: String?): Int {
        return if (isPrint && msg != null) android.util.Log.i(tag, msg) else -1
    }

    fun w(tag: String, msg: String?): Int {
        return if (isPrint && msg != null) android.util.Log.w(tag, msg) else -1
    }

    fun e(tag: String, msg: String?): Int {
        return if (isPrint && msg != null) android.util.Log.e(tag, msg) else -1
    }

    /**
     * ******************** Log with Throwable **************************
     */
    fun v(tag: String, msg: String?, tr: Throwable): Int {
        return if (isPrint && msg != null) android.util.Log.v(tag, msg, tr) else -1
    }

    fun d(tag: String, msg: String?, tr: Throwable): Int {
        return if (isPrint && msg != null) android.util.Log.d(tag, msg, tr) else -1
    }

    fun i(tag: String, msg: String?, tr: Throwable): Int {
        return if (isPrint && msg != null) android.util.Log.i(tag, msg, tr) else -1
    }

    fun w(tag: String, msg: String?, tr: Throwable): Int {
        return if (isPrint && msg != null) android.util.Log.w(tag, msg, tr) else -1
    }

    fun e(tag: String, msg: String?, tr: Throwable): Int {
        return if (isPrint && msg != null) android.util.Log.e(tag, msg, tr) else -1
    }

}
