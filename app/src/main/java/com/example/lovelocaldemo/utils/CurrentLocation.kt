package com.example.lovelocaldemo.utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import androidx.core.app.ActivityCompat
import com.example.lovelocaldemo.listener.LocationInterface


/**
 * This class is used to get location using location manger*/
class CurrentLocation(val activity: Activity, var listener: LocationInterface) {
    val REQUEST_LOCATION = 1
    private var locationManager: LocationManager? = null
    private var latitude: Double? = null
    private var longitude: Double? = null

    init {
        getPermission()
    }

    private fun getPermission() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_LOCATION
        )

        locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) == false) {
            onGPS()
        } else {
            getLocation()
        }
    }

    private fun onGPS() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes",
            DialogInterface.OnClickListener { dialog, which ->
                activity.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            })
            .setNegativeButton(
                "No",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                activity, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION
            )
        } else {
            val locationGps = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (locationGps != null) {
                latitude = locationGps.latitude
                longitude = locationGps.longitude
                listener.getLocation(locationGps)
            } else {
                listener.getLocation(null)
                activity.showToastLong("Unable to find location")
            }

        }
    }

}

