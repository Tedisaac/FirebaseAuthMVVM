package com.ted.firebaseauthenticationmvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ted.firebaseauthenticationmvvm.databinding.FragmentSignUpBinding
import com.ted.firebaseauthenticationmvvm.utils.CustomViews
import com.ted.firebaseauthenticationmvvm.viewmodels.AuthViewModel

class SignUpFragment : Fragment() {

    private var _signUpBinding: FragmentSignUpBinding? = null
    private val signUpBinding get() = _signUpBinding!!

    private val authViewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authViewModel.getUserLiveData().observe(this){ result->
            if (result != null){
                findNavController().navigate(R.id.action_signUpFragment_to_detailsFragment)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _signUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)

        signUpBinding.txtSignUpTwo.setOnClickListener { validateData() }
        signUpBinding.txtSignInTwo.setOnClickListener { switchToSignInScreen() }

        return signUpBinding.root
    }

    private fun validateData() {
        val email = signUpBinding.edEmailSignUp.text.toString()
        val password = signUpBinding.edPasswordSignUp.text.toString()
        val confirmPassword = signUpBinding.edConfirmPasswordSignUp.text.toString()

        if (email.isEmpty()){
            CustomViews.hideSoftKeyBoard(requireActivity(), signUpBinding.root)
            CustomViews.showSnackBar(signUpBinding.root, "Email Required", requireActivity())
        }else  if (password.isEmpty()){
            CustomViews.hideSoftKeyBoard(requireActivity(), signUpBinding.root)
            CustomViews.showSnackBar(signUpBinding.root, "Password Required", requireActivity())
        }else  if (confirmPassword.isEmpty()){
            CustomViews.hideSoftKeyBoard(requireActivity(), signUpBinding.root)
            CustomViews.showSnackBar(signUpBinding.root, "Confirm Password Required", requireActivity())
        }else  if (password != confirmPassword){
            CustomViews.hideSoftKeyBoard(requireActivity(), signUpBinding.root)
            CustomViews.showSnackBar(signUpBinding.root, "Passwords do not match!!", requireActivity())
        }else{
            signUp(email, password, signUpBinding.root)
        }
    }

    private fun signUp(email: String, password: String, view: View) {
        authViewModel.signUp(email, password, view, requireActivity())
    }

    private fun switchToSignInScreen() {
        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _signUpBinding = null
    }

}