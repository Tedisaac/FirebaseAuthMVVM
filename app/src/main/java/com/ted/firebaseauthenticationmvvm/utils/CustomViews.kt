package com.ted.firebaseauthenticationmvvm.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.ted.firebaseauthenticationmvvm.R

object CustomViews {

    fun showSnackBar(view: View, message: String){
        val snackbar: Snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        val sbView = snackbar.view
        //sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.blue))
        snackbar.show()
    }
}