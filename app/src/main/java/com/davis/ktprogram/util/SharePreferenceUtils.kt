package com.davis.ktprogram.util

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences


import com.davis.ktprogram.AppApplication

/**
 */
class SharePreferenceUtils private constructor(fileName: String) {

    private var sp: SharedPreferences? = null

    init {
        sp = mContext!!.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }

    fun putString(key: String, value: String) {
        sp!!.edit().putString(key, value).commit()
    }

    fun putLong(key: String, value: Long) {
        sp!!.edit().putLong(key, value).commit()
    }

    fun putBoolean(key: String, value: Boolean) {
        sp!!.edit().putBoolean(key, value).commit()
    }

    fun putInt(key: String, value: Int) {
        sp!!.edit().putInt(key, value).commit()
    }

    @TargetApi(11)
    fun putStringSet(key: String, values: Set<String>) {
        sp!!.edit().putStringSet(key, values).commit()
    }

    fun putFloat(key: String, value: Float) {
        sp!!.edit().putFloat(key, value).commit()
    }

    fun getString(key: String, defValue: String): String {
        return sp!!.getString(key, defValue)
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return sp!!.getBoolean(key, defValue)
    }

    fun getFloat(key: String, defValue: Float): Float {
        return sp!!.getFloat(key, defValue)
    }

    fun getInt(key: String, defValue: Int): Int {
        return sp!!.getInt(key, defValue)
    }

    fun getLong(key: String, defValue: Long): Long {
        return sp!!.getLong(key, defValue)

    }

    fun clear() {
        sp!!.edit().clear().commit()
    }

    companion object {
        private var mContext: Context? = null

        init {
            mContext = AppApplication.getApplication()
        }

        fun getSharedPreferences(fileName: String): SharePreferenceUtils {
            return SharePreferenceUtils(fileName)
        }

        val sharedPreferences: SharePreferenceUtils
            get() = SharePreferenceUtils("sdj")
    }
}
