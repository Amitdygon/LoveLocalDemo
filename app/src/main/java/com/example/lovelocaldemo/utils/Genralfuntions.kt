package com.example.lovelocaldemo.utils

import android.content.Context
import android.location.Geocoder
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast

fun Context.showToastLong(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

@Suppress("DEPRECATION")
fun Geocoder.getAddress(
    latitude: Double,
    longitude: Double,
    address: (android.location.Address?) -> Unit
) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getFromLocation(latitude, longitude, 1) { address(it.firstOrNull()) }
        return
    }
    try {
        address(getFromLocation(latitude, longitude, 1)?.firstOrNull())
    } catch (e: Exception) {
        //will catch if there is an internet problem
        Log.e("+++++",""+e.message)
        address(null)
    }
}
