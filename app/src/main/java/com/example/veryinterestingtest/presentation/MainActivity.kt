package com.example.veryinterestingtest.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.veryinterestingtest.R
import com.example.veryinterestingtest.core.entity.SearchQuery
import com.example.veryinterestingtest.core.usecases.SearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val searchRepo: SearchUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lifecycleScope.launch(Dispatchers.IO) {
            searchRepo.search(SearchQuery("apple")).fold(
                onSuccess = {
                    Log.wtf("AAAAA", "Success: ${ it.first().title }")

                },
                onFailure = {
                    Log.wtf("AAAAA", "Error: ${ it.message }")
                }
            )
        }
    }

}

