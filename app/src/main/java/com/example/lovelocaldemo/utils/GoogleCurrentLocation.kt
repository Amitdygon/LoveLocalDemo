package com.example.lovelocaldemo.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import androidx.core.app.ActivityCompat
import com.example.lovelocaldemo.listener.LocationInterface
import com.google.android.gms.location.LocationServices
import java.util.*


class GoogleCurrentLocation(var activity: Activity, var listener: LocationInterface) {
    val REQUEST_LOCATION = 1


    fun checkLocationPermission() {
        // Get the last known location. In some rare situations, this can be null.
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION
            )
            return
        }

        getCurrentLocation()


    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        val client = LocationServices.getFusedLocationProviderClient(activity)
        val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            client.lastLocation.addOnSuccessListener { location: Location? ->
                listener.getLocation(location)
            }
        } else {
            onGPS()
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



}