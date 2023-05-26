package com.example.lovelocaldemo.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

fun Context.showToastLong(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}
fun View.gone(){
    this.visibility = View.GONE
}
fun View.visible(){
    this.visibility = View.VISIBLE
}
