package com.astropics.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.astropics.R
import com.astropics.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        binding.btnDisplay.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_displayFragment)
        }

        binding.btnFavourites.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favouritesFragment)
        }
    }
}