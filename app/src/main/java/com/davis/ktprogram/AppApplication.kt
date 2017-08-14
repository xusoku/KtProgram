package com.davis.ktprogram

import android.app.Application
import android.content.Context
import android.text.TextUtils
import com.davis.ktprogram.model.*
import com.davis.ktprogram.util.SharePreferenceUtils
import java.util.ArrayList

/**
 * Created by xushengfu on 2017/8/10.
 */
class AppApplication : Application() {



    companion object {

        private var instance: AppApplication? = null

        fun getApplication(): AppApplication? {
            return instance
        }

        val apptype = "android"
        var shopid = ""
        var token = ""
        var userInfo: UserInfo? = null
        var extendedinfo: Extendedinfo? = null
        var address: Address? = null

        val kefu = "4009216797"

        var shoplist: ArrayList<Shop> = ArrayList()

        var classiclist: ArrayList<Category> = ArrayList()


        fun isLogin(context: Context): Boolean {
            token = SharePreferenceUtils.sharedPreferences.getString("token", "")
            if (TextUtils.isEmpty(token)) {
//                LoginActivity.jumpLoginActivity(instance)
                return false
            } else {
                return true
            }
        }

        fun getCouponcount(): String?{
            if (extendedinfo != null) {
                return extendedinfo?.couponcount
            } else {
                return ""
            }
        }

        fun getFcurrmoney(): String? {
            if (extendedinfo != null) {
                return extendedinfo?.fcurrmoney
            } else {
                return ""
            }
        }

        fun getCartcount(): String? {
            if (extendedinfo != null) {
                return extendedinfo?.cartcount
            } else {
                return ""
            }
        }


        fun getOrderall(): String? {
            if (extendedinfo != null) {
                return extendedinfo?.orderall
            } else {
                return ""
            }
        }

        fun getOrdersending(): String? {
            if (extendedinfo != null) {
                return extendedinfo?.ordersending
            } else {
                return ""
            }
        }

        fun getOrderunpaid(): String? {
            if (extendedinfo != null) {
                return extendedinfo?.orderunpaid
            } else {
                return ""
            }
        }

        fun getOrderwaitsend(): String? {
            if (extendedinfo != null) {
                return extendedinfo?.orderwaitsend
            } else {
                return ""
            }
        }
    }

    //充值还是支付
    var isYue = false
    //订单号
    var numberCode = ""

    override fun onCreate() {
        super.onCreate()

        instance = getApplicationContext() as AppApplication?

        token = SharePreferenceUtils.sharedPreferences.getString("token", "")

        shopid = SharePreferenceUtils.sharedPreferences.getString("shopid", "")
    }





}