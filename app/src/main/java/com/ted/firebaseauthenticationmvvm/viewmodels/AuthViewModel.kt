package com.ted.firebaseauthenticationmvvm.viewmodels

import android.app.Application
import android.content.Context
import android.view.View
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

    fun signUp(email: String, password: String, view: View, context: Context){
        authRepository.signUp(email, password, view, context)
    }

    fun signIn (email: String, password: String, view: View, context: Context){
        authRepository.signIn(email, password, view, context)
    }



}