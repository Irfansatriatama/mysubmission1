package com.irfan.mysubmission1.Presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.irfan.mysubmission1.R
import com.irfan.mysubmission1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //buat viewModel

    companion object {
        private const val TAG = "MainActivty"
        private const val SocMed_ID = "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"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //buat viewModel

    }

    private fun showLoading(isLoading: Boolean){
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        } else{
            binding.progressBar.visibility = View.GONE
        }
    }
}