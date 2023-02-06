package com.ted.firebaseauthenticationmvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ted.firebaseauthenticationmvvm.databinding.FragmentDetailsBinding
import com.ted.firebaseauthenticationmvvm.viewmodels.SignedInViewModel

class DetailsFragment : Fragment() {

    private var _detailsBinding: FragmentDetailsBinding? = null
    private val detailsBinding get() = _detailsBinding!!

    private val signedInViewModel by lazy {
        ViewModelProvider(this)[SignedInViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signedInViewModel.getUserLiveData().observe(this){ result->
            if (result == null){
                findNavController().navigate(R.id.action_detailsFragment_to_signInFragment)
            }else{
                detailsBinding.txtUsername.text = result.email
            }
        }

        signedInViewModel.getLoggedOutLivedata().observe(this){ result->
            if (result){
                findNavController().navigate(R.id.action_detailsFragment_to_signInFragment)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _detailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)

        detailsBinding.txtUsername.text  = "Isaac"
        detailsBinding.fabSignOut.setOnClickListener { switchToSignInScreen() }

        return detailsBinding.root
    }

    private fun switchToSignInScreen() {
        signedInViewModel.signOut()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _detailsBinding = null
    }
}