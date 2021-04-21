package com.example.clothes_shoppingkotlin.CacheInterceptors

import android.content.Context
import com.example.clothes_shoppingkotlin.Network.NetworkInfo
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ForcedCache(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (!NetworkInfo(context).isNetworkAvailable) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
        }
        return chain.proceed(builder.build())
    }

}
