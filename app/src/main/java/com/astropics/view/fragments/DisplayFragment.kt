package com.astropics.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.astropics.R
import com.astropics.databinding.FragmentDisplayBinding
import com.astropics.utils.DataHandler
import com.astropics.utils.LogData
import com.astropics.utils.loadImageFromGlide
import com.astropics.viewmodel.DisplayViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayFragment : Fragment(R.layout.fragment_display) {

    private lateinit var binding: FragmentDisplayBinding

    val viewModel: DisplayViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDisplayBinding.bind(view)
        init()
    }

    private fun init() {
        binding.btnFavourite.setOnClickListener { viewModel.toggleFavourite() }

        viewModel.pic.observe(viewLifecycleOwner) { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.imageView.loadImageFromGlide(dataHandler.data?.hdUrl)
                    binding.tvTitle.text = dataHandler.data?.title
                    binding.tvDesc.text = dataHandler.data?.explanation
                    binding.tvDate.text =
                        String.format(getString(R.string.posted_on), dataHandler.data?.picDate)
                    binding.btnFavourite.visibility = View.VISIBLE
                    if (dataHandler.data?.isFavourite!! == 1) {
                        binding.btnFavourite.text = getString(R.string.remove_from_favourites)
                    } else {
                        binding.btnFavourite.text = getString(R.string.add_to_favourites)
                    }
                }
                is DataHandler.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnFavourite.visibility = View.GONE
                    LogData("onViewCreated: ERROR " + dataHandler.message)
                }
                is DataHandler.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnFavourite.visibility = View.GONE
                    LogData("onViewCreated: LOADING..")
                }
            }
        }

        viewModel.getPicByDate()
    }
}