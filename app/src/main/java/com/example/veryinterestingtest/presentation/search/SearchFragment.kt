package com.example.veryinterestingtest.presentation.search

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.veryinterestingtest.R
import com.example.veryinterestingtest.databinding.FragmentSearchBinding
import com.example.veryinterestingtest.presentation.base.BaseFragment
import com.example.veryinterestingtest.presentation.search.adapter.ImageAdapter
import com.example.veryinterestingtest.presentation.search.state.SearchScreenState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(FragmentSearchBinding::inflate) {

    override val viewModel: SearchViewModel by viewModel()
    private val adapter by lazy {
        ImageAdapter(
            openInWebListener = viewModel::openInWeb,
            showFullScreenListener = viewModel::showFullScreen
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(requireActivity().window) {
            statusBarColor = ContextCompat.getColor(requireContext(), R.color.elements)
        }
        setupSearchField()
        binding.results.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {

            observeState()

        }
        viewModel.screenState
    }

    private fun setupSearchField() {
        binding.searchField.doAfterTextChanged {
            viewModel.search(it.toString())
        }
    }

    private suspend fun observeState() {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.screenState.collect { state ->
                when (state) {
                    is SearchScreenState.Loading -> {
                        show(progressBar = true)
                    }
                    is SearchScreenState.Results -> {
                        show(results = true)
                        (binding.results.adapter as ImageAdapter).submitData(lifecycle, state.images)
                    }
                    is SearchScreenState.Error -> {
                        show(error = true)
                        binding.error.text = state.message
                    }
                    is SearchScreenState.Empty -> {
                        show()
                    }
                }
            }
        }
    }
    private fun show(
        results: Boolean = false,
        progressBar: Boolean = false,
        error: Boolean = false
    ) {
        with(binding) {
            this.results.isVisible = results
            this.progressBar.isVisible = progressBar
            this.error.isVisible = error
        }
    }

}