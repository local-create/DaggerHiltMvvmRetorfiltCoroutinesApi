package com.app.daggrhiltdemo.base

import android.app.ProgressDialog
import android.content.Context

class BaseActivity(val context: Context) {
    private var progressDialog: ProgressDialog? = null

    fun showProgress() {
        progressDialog = ProgressDialog(context)
        progressDialog!!.setMessage("Please wait...")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }

    fun hideProgress() {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
            progressDialog == null
        }
    }
}