package com.example.mymovies.mvvm.view.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class Utility {

    companion object {

        @Volatile
        private var INSTANCE: Utility? = null

        fun getInstance(): Utility? {
            if (INSTANCE == null) {
                synchronized(Utility::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Utility()
                    }
                }
            }
            return INSTANCE
        }
    }

    public fun isOnline(appContext : Application): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}