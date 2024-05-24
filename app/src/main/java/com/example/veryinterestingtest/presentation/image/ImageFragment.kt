package com.example.veryinterestingtest.presentation.image

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.veryinterestingtest.R
import com.example.veryinterestingtest.databinding.FragmentImageBinding
import com.example.veryinterestingtest.presentation.base.BaseFragment
import com.example.veryinterestingtest.presentation.image.state.ImageScreenState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageFragment : BaseFragment<FragmentImageBinding, ImageViewModel>(FragmentImageBinding::inflate) {

    override val viewModel: ImageViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect { state ->
                    when (state) {
                        is ImageScreenState.Empty -> { }
                        is ImageScreenState.Data -> {}
                        is ImageScreenState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        with(binding) {
            loadImage.setOnClickListener {
                viewModel.loadImage(image)
            }

            navigateBack.setOnClickListener {
                viewModel.navigate(R.id.action_imageFragment_to_searchFragment)
            }

            openInWeb.setOnClickListener {
                viewModel.openInWeb(requireContext())
            }
        }
    }
}