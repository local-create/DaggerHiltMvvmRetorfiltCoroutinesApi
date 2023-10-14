package com.app.retrofitwithcoroutines.session

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager

class SharedPreferenceHelper(context: Context) {
    val sharedPreferences: SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences("daggerWithCoroutinesDemo", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun getValue(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }

    fun setValue(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
        editor.apply()
    }

    fun clear() {
        editor.clear()
        editor.commit()
        editor.apply()
    }
    companion object {
        fun isConnectingToInternet(context: Context): Boolean {
            val connectivity =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivity.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }

        fun hideKeyboard(activity: Activity) {
            val view: View =
                (if (activity.currentFocus == null) View(activity) else activity.currentFocus)!!
            val inputMethodManager =
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}