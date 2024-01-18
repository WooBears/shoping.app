package com.example.shoping.fragments.loginRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.shoping.R
import com.example.shoping.databinding.FragmentAccountOptionBinding


class AccountOptionFragment : Fragment() {

    private lateinit var binding: FragmentAccountOptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountOptionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            btLoginAccountOptions.setOnClickListener {
                findNavController().navigate(R.id.action_accountOptionFragment_to_loginFragment)
            }

            btRegisterAccountOptions.setOnClickListener {
                findNavController().navigate(R.id.action_accountOptionFragment_to_registerFragment)
            }
        }
    }

}