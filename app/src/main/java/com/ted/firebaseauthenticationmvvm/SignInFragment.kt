package com.ted.firebaseauthenticationmvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ted.firebaseauthenticationmvvm.databinding.FragmentSignInBinding
import com.ted.firebaseauthenticationmvvm.utils.CustomViews
import com.ted.firebaseauthenticationmvvm.viewmodels.AuthViewModel


class SignInFragment : Fragment() {
    private var _signInBinding: FragmentSignInBinding? = null

    private val signInBinding get() = _signInBinding!!

    private val authViewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authViewModel.getUserLiveData().observe(this){ result->
            if (result != null){
                findNavController().navigate(R.id.action_signInFragment_to_detailsFragment)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _signInBinding = FragmentSignInBinding.inflate(inflater, container, false)

        signInBinding.txtSignIn.setOnClickListener { validateData() }
        signInBinding.txtSignUp.setOnClickListener { switchToSignUpScreen() }

        return signInBinding.root
    }

    private fun switchToSignUpScreen() {
        findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
    }

    private fun validateData() {
        val email = signInBinding.edEmailSignIn.text.toString()
        val password = signInBinding.edPasswordSignIn.text.toString()

        if (email.isEmpty()){
            CustomViews.hideSoftKeyBoard(requireActivity(), signInBinding.root)
            CustomViews.showSnackBar(signInBinding.root, "Email Required", requireActivity())
        }
        else if (password.isEmpty()){
            CustomViews.hideSoftKeyBoard(requireActivity(), signInBinding.root)
            CustomViews.showSnackBar(signInBinding.root, "Password Required", requireActivity())
        }else
        {
            signIn(email, password, signInBinding.root)
        }

    }

    private fun signIn(email: String, password: String, view: View) {
        authViewModel.signIn(email, password, view, requireActivity())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _signInBinding = null
    }

}