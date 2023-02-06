package com.ted.firebaseauthenticationmvvm.utils

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.ted.firebaseauthenticationmvvm.R

object CustomViews {

    lateinit var loadingdialog: Dialog
    fun showLoadingDialog(context: Context) {
        loadingdialog = Dialog(context, R.style.CustomAlertDialog)
        loadingdialog.setContentView(R.layout.dialog_layout)
        loadingdialog.setCancelable(true)
        val width = (context.resources.displayMetrics.widthPixels * 0.80).toInt()
        val height = (context.resources.displayMetrics.heightPixels * 0.20).toInt()
        loadingdialog.window!!.setLayout(width, height)
        loadingdialog.create()
        loadingdialog.show()
    }

    fun dismissLoadingDialog(){
        if (loadingdialog != null){
            loadingdialog.dismiss()
        }
    }

    fun showSnackBar(view: View, message: String, context: Context){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(context,R.color.blue))
            .show()
    }

    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }

    }
}