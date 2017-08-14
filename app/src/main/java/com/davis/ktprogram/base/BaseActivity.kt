package com.davis.ktprogram.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.davis.ktprogram.R
import com.davis.ktprogram.util.LogUtils

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)


        LogUtils.e("getSimpleName", this.javaClass.simpleName)

    }
}
