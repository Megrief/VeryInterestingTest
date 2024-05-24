package com.example.veryinterestingtest.presentation.search

import android.os.Bundle
import android.view.View
import com.example.veryinterestingtest.R
import com.example.veryinterestingtest.databinding.FragmentSearchBinding
import com.example.veryinterestingtest.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(FragmentSearchBinding::inflate) {

    override val viewModel: SearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigateButton.setOnClickListener {
            viewModel.navigate(R.id.action_searchFragment_to_imageFragment)
        }
    }
}