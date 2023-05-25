package com.example.lovelocaldemo.data.viewModel

import com.example.lovelocaldemo.LoveLocalApplication
import com.example.lovelocaldemo.R


/**
 * This is common validation validation model used to check validation type throught the application
 *
 * @property message error message that is to show
 * @property type identify the type of erro if there are mutiple error meassage on the same screen
 */
data class DataValidation(
    val message: String = LoveLocalApplication.applicationContext()
        .getString(R.string.something_went_wrong),
    var type: Int = 0
)
