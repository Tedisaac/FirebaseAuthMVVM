package com.ted.firebaseauthenticationmvvm.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.ted.firebaseauthenticationmvvm.repository.AuthRepository

class SignedInViewModel(application: Application) : AndroidViewModel(application) {

    private var authRepository: AuthRepository
    private var userLiveData: MutableLiveData<FirebaseUser>
    private var loggedOutLiveData: MutableLiveData<Boolean>

    init {
        authRepository = AuthRepository(application)
        userLiveData = authRepository.getUserLiveData()
        loggedOutLiveData = authRepository.getLoggedOutLiveData()
    }

    fun getUserLiveData() : MutableLiveData<FirebaseUser>{
        return userLiveData
    }

    fun getLoggedOutLivedata() : MutableLiveData<Boolean>{
        return loggedOutLiveData
    }

    fun signOut(){
        authRepository.signOut()
    }
}