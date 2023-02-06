package com.ted.firebaseauthenticationmvvm.repository

import android.app.Application
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ted.firebaseauthenticationmvvm.utils.CustomViews

class AuthRepository(application: Application) {

    private var application: Application
    private var firebaseAuth: FirebaseAuth? = null
    private var userLiveData: MutableLiveData<FirebaseUser>? =  null
    private var loggedOutLiveData: MutableLiveData<Boolean>? = null

    init {

        this.application  = application
        firebaseAuth = FirebaseAuth.getInstance()
        userLiveData = MutableLiveData()
        loggedOutLiveData = MutableLiveData()

        if (firebaseAuth?.currentUser != null){
            userLiveData?.value = firebaseAuth!!.currentUser
            loggedOutLiveData?.value = false
        }
    }

    fun getUserLiveData() : MutableLiveData<FirebaseUser>{
        return  userLiveData!!
    }

    fun getLoggedOutLiveData() : MutableLiveData<Boolean>{
        return  loggedOutLiveData!!
    }

    fun signUp(email: String, password: String, view: View, context: Context){
        CustomViews.showLoadingDialog(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            firebaseAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener(application.mainExecutor!!) { task ->
                if (task.isSuccessful) {
                    CustomViews.dismissLoadingDialog()
                    userLiveData?.value = firebaseAuth?.currentUser
                }
            }?.addOnFailureListener(application.mainExecutor) { exception->
                CustomViews.dismissLoadingDialog()
                CustomViews.showSnackBar(view, exception.message.toString(), context)
            }
        }
    }

    fun signIn(email: String, password: String, view: View, context: Context){
        CustomViews.showLoadingDialog(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            firebaseAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener(application.mainExecutor!!) { task ->
                if (task.isSuccessful){
                    CustomViews.dismissLoadingDialog()
                    userLiveData?.value = firebaseAuth?.currentUser
                }
            }?.addOnFailureListener(application.mainExecutor) { exception->
                CustomViews.dismissLoadingDialog()
                CustomViews.showSnackBar(view, exception.message.toString(), context)
            }
        }
    }

    fun signOut(){
        firebaseAuth?.signOut()
        loggedOutLiveData?.value = true
    }

}