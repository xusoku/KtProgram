package com.davis.ktprogram.api

import com.davis.ktprogram.util.LogUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by davis on 16/5/17.
 */
object ApiInstant {


    private var service: ApiService? = null


    //            OkHttpClient httpClient = new OkHttpClient();
    //            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    //            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    //            httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
    val instant: ApiService
        get() {

            if (service == null) {

                val gson = GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                        .create()

                val httpClient = OkHttpClient.Builder()
                        .addNetworkInterceptor { chain ->
                            val response = chain.proceed(chain.request())
                            LogUtils.e("url", response.request().url().toString())
                            response
                        }
                        .build()


                val retrofit = Retrofit.Builder()
                        .baseUrl(ApiService.baseurl)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(httpClient)
                        .build()


                service = retrofit.create(ApiService::class.java)

            }
            return service!!
        }


}
