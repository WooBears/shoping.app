package com.example.shoping.fragments.loginRegister

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shoping.R
import com.example.shoping.data.User
import com.example.shoping.databinding.FragmentRegisterBinding
import com.example.shoping.util.RegisterValidation
import com.example.shoping.util.Resource
import com.example.shoping.viewmodel.RegisterVIewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val TAG = "RegisterFragment"
@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val vIewModel by viewModels<RegisterVIewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDoYouHaveAnAccount.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.apply {
            btRegister.setOnClickListener {
                val user = User(
                    edNameText.text.toString().trim(),
                    edLastNameText.text.toString().trim(),
                    edEmailText.text.toString().trim()
                )
                val password = edPasswordRegisterText.text.toString()
                vIewModel.createAccountWithEmailAndPassword(user,password)
            }
        }

        lifecycleScope.launchWhenStarted {
            vIewModel.register.collect{
                when (it)
                {
                    is Resource.Loading ->{
                        binding.btRegister.startAnimation()
                    }
                    is Resource.Success ->{
                        Log.d("text",it.data.toString())
                        binding.btRegister.revertAnimation()
                    }
                    is Resource.Error ->{
                        Log.e(TAG,it.message.toString())
                        binding.btRegister.revertAnimation()
                    }

                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            vIewModel.validation.collect{validation ->
                if (validation.email is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {
                        binding.edEmailText.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                }

                if (validation.password is RegisterValidation.Failed)
                {
                    withContext(Dispatchers.Main){
                        binding.edPasswordRegisterText.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }
            }
        }
    }
}