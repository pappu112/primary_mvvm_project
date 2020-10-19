package com.example.myapplication.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.myapplication.util.NoInterNetException
import okhttp3.Interceptor
import okhttp3.Response

class NetWorkConnectionIntercetper(
    context: Context
) : Interceptor{

    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isInternetAvailable())
            throw NoInterNetException("Mack sure you have active internet connection")
        return chain.proceed(chain.request())

    }

    private fun isInternetAvailable(): Boolean{
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it !=null && it.isConnected
        }
    }
}