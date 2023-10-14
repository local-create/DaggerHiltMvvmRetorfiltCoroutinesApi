package com.app.retrofitwithcoroutines.utils

import android.app.Activity
import android.widget.Toast

object Constants {
    const val BASE_URL = "https://api.escuelajs.co/api/v1/auth/"
}

fun Activity.showToast(msg: String) {
    if (msg != null) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}