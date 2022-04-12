package com.astropics.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.astropics.R
import com.astropics.databinding.FragmentFavouritesBinding
import com.astropics.view.adapter.FavouritesAdapter
import com.astropics.viewmodel.FavouritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    lateinit var binding: FragmentFavouritesBinding
    val viewModel: FavouritesViewModel by viewModels()

    @Inject
    lateinit var favsAdapter: FavouritesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavouritesBinding.bind(view)
        init()
    }

    private fun init() {
        binding.recyclerview.apply {
            adapter = favsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.favList.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                binding.tvEmpty.text = getString(R.string.empty_error)
            } else {
                binding.tvEmpty.text = ""
                favsAdapter.differ.submitList(it)
            }
        }

        viewModel.getFavouriteList()

    }
}