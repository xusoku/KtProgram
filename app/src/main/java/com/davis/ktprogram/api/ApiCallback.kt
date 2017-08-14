package com.davis.ktprogram.api


import android.util.Log


import com.davis.ktprogram.model.BaseModel
import com.davis.ktprogram.util.ToastUitl

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by davis on 16/5/17.
 */
abstract class ApiCallback<T> : Callback<T> {

    abstract fun onSucssce(t: T)

    abstract fun onFailure()

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.code() == 200) {
            val (breturn, errorinfo) = response.body() as BaseModel<*>
            if (breturn) {
                onSucssce(response.body())
            } else {
                onFailure()
                ToastUitl.showToast("" + errorinfo)
            }
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure()
        Log.e("aaa", t.message.toString())
        ToastUitl.showToast("网络异常")
    }

    /* @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() == 200) {
            BaseModel t = (BaseModel) response.body();
            if (t.getBreturn()) {
                onSucssce(response.body());
            } else {
                onFailure();
                ToastUitl.showToast("" + t.getErrorinfo());
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailure();
        Log.e("aaa", t.getMessage().toString());
        ToastUitl.showToast("网络异常");
    }*/
}