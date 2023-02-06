package com.ted.firebaseauthenticationmvvm.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.ted.firebaseauthenticationmvvm.repository.AuthRepository

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private var authRepository: AuthRepository
    private var userLiveData: MutableLiveData<FirebaseUser>

    init {
        authRepository = AuthRepository(application)
        userLiveData = authRepository.getUserLiveData()
    }

    fun getUserLiveData() : MutableLiveData<FirebaseUser>{
        return userLiveData
    }

    fun signUp(email: String, password: String){
        authRepository.signUp(email, password)
    }

    fun signIn (email: String, password: String){
        authRepository.signIn(email, password)
    }



}