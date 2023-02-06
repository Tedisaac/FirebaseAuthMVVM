package com.ted.firebaseauthenticationmvvm.repository

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

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

    fun signUp(email: String, password: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            firebaseAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener(application.mainExecutor!!) { task ->
                if (task.isSuccessful){
                    userLiveData?.value = firebaseAuth?.currentUser
                }else{
                    Toast.makeText(application.applicationContext, "Failed!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun signIn(email: String, password: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            firebaseAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener(application.mainExecutor!!) { task ->
                if (task.isSuccessful){
                    userLiveData?.value = firebaseAuth?.currentUser
                }else{
                    Toast.makeText(application.applicationContext, "Failed!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun signOut(){
        firebaseAuth?.signOut()
        loggedOutLiveData?.value = true
    }

}