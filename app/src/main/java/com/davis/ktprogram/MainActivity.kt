package com.davis.ktprogram

import android.os.Bundle
import com.davis.ktprogram.api.ApiCallback
import com.davis.ktprogram.api.ApiInstant
import com.davis.ktprogram.base.BaseActivity
import com.davis.ktprogram.model.BaseModel
import com.davis.ktprogram.model.Shop
import com.davis.ktprogram.util.LogUtils
import java.util.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var call = ApiInstant.instant.getShoplist(AppApplication.apptype)
        call.enqueue(object : ApiCallback<BaseModel<ArrayList<Shop>>>() {
            override fun onSucssce(baseModel: BaseModel<ArrayList<Shop>>) {

                val listShop=baseModel.`object`;
                LogUtils.e("asdf",listShop.toString())
            }

            override fun onFailure() {

            }
        })
    }

}
