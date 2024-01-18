package com.example.shoping.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shoping.R
import com.example.shoping.databinding.ActivityShopingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopingActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityShopingBinding.inflate(layoutInflater)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.shoppingHostFragment)
        binding.bottomNavigation.setupWithNavController(navController)
    }
}