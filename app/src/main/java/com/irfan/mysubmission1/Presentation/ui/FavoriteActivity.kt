package com.irfan.mysubmission1.Presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.mysubmission1.Presentation.ViewModel.FavoriteViewModel
import com.irfan.mysubmission1.Presentation.ViewModel.FollowViewModel
import com.irfan.mysubmission1.R
import com.irfan.mysubmission1.data.db.DatabaseModule
import com.irfan.mysubmission1.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel by viewModels<FavoriteViewModel> {
        FavoriteViewModel.Factory(DatabaseModule(this))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.adapter =
    }
}