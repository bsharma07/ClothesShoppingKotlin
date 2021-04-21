package com.example.clothes_shoppingkotlin.Network

import android.content.Context
import com.example.clothes_shoppingkotlin.CacheInterceptors.CacheIntercept
import com.example.clothes_shoppingkotlin.CacheInterceptors.ForcedCache
import com.example.clothes_shoppingkotlin.Utils.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null
    fun getClient(mContext: Context?): Retrofit? {
        /*val builder = OkHttpClient().newBuilder()
            .addNetworkInterceptor(CacheIntercept())
            .addInterceptor(mContext?.let { ForcedCache(it) }).build()*/
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constant.Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}