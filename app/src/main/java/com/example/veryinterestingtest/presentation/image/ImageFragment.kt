package com.example.veryinterestingtest.presentation.image

import android.os.Bundle
import android.view.View
import com.example.veryinterestingtest.databinding.FragmentImageBinding
import com.example.veryinterestingtest.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageFragment : BaseFragment<FragmentImageBinding, ImageViewModel>(FragmentImageBinding::inflate) {

    override val viewModel: ImageViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
    }
}