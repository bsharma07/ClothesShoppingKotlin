package com.example.clothes_shoppingkotlin.Network

import android.content.Context
import android.net.ConnectivityManager

class NetworkInfo(private val mContext: Context) {
    //Boolean hasCellular = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
    val isNetworkAvailable: Boolean
        get() {
            val connMgr =
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val hasWifi =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.isConnected
            //Boolean hasCellular = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
            val networkInfo = connMgr.activeNetworkInfo ?: return false
            val isConnected = connMgr.activeNetworkInfo!!.isConnected
            return hasWifi && isConnected
        }

}