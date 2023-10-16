package com.example.shoping.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoping.R
import com.example.shoping.adapters.HomeViewpagerAdapter
import com.example.shoping.databinding.FragmentHomeBinding
import com.example.shoping.fragments.categories.AccessoryFragment
import com.example.shoping.fragments.categories.ChairFragment
import com.example.shoping.fragments.categories.CupboardFragment
import com.example.shoping.fragments.categories.FurnitureFragment
import com.example.shoping.fragments.categories.MainCategoryFragment
import com.example.shoping.fragments.categories.TableFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragment = arrayListOf<Fragment>(
            MainCategoryFragment(),
            ChairFragment(),
            CupboardFragment(),
            TableFragment(),
            AccessoryFragment(),
            FurnitureFragment()
        )

        val viewPager2Adapter = HomeViewpagerAdapter(categoriesFragment, childFragmentManager, lifecycle)
        binding.idViewpagerHome.adapter = viewPager2Adapter
        TabLayoutMediator(binding.idTabLayout, binding.idViewpagerHome){tab,position ->
            when(position){
                0 -> tab.text = "Main"
                1 -> tab.text = "Chair"
                2 -> tab.text = "Cupboard"
                3 -> tab.text = "Table"
                4 -> tab.text = "Accessory"
                5 -> tab.text = "Furniture"
            }
        }.attach()
    }

}