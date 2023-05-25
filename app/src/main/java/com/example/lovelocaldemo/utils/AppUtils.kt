package com.example.lovelocaldemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.lovelocaldemo.LoveLocalApplication
import com.example.lovelocaldemo.R
import java.util.*


object AppUtils {

    /**
     * Check internet connect is on or not */
    fun isInternetAvailable(context: Context? = LoveLocalApplication.applicationContext()): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            nwInfo.isConnected
        }
    }

    /***
     * Get local current time
     * set the message on the bass of current time
     */

    fun getLocalTimeMessage(): String {
        val c: Calendar = Calendar.getInstance()
        return when (c.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> {
                LoveLocalApplication.applicationContext().getString(R.string.good_morning)
            }
            in 12..15 -> {
                LoveLocalApplication.applicationContext().getString(R.string.good_afternoon)
            }
            in 16..20 -> {
                LoveLocalApplication.applicationContext().getString(R.string.good_evening)
            }
            in 21..23 -> {
                LoveLocalApplication.applicationContext().getString(R.string.good_night)
            }
            else -> ""
        }
    }

    fun dpToPx(context: Context, dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }


}