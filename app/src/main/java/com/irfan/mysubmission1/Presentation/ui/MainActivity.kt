package com.irfan.mysubmission1.Presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.mysubmission1.Presentation.Adapter.FollowAdapter
import com.irfan.mysubmission1.Presentation.ViewModel.FollowViewModel
import com.irfan.mysubmission1.data.response.FollowResponse
import com.irfan.mysubmission1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val followViewModel by viewModels<FollowViewModel>()

    companion object {

        private const val myUsername = "irfansatriatama"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (followViewModel.followers.value == null && followViewModel.followings.value == null){
            followViewModel.getDetailFollower(myUsername)
            followViewModel.getListFollow(myUsername)
        }

        followViewModel.isLoading.observe(this) { loader ->
            showLoadingProcess(loader)
        }
        followViewModel.followers.observe(this) { followers ->
            if (followers != null) {
                loadAllFollowers(followers)
            }
        }

        with(binding){
            binding.apply {
                rvSearchUsers.layoutManager = LinearLayoutManager(this@MainActivity)
                searchView.setupWithSearchBar(binding.searchBar)
                searchView
                    .editText
                    .setOnEditorActionListener { _, _, _ ->
                        searchBar.text = searchView.text
                        searchView.hide()
                        followViewModel.getResultBySearchUsername(searchView.text.toString())
                        true
                    }
            }
        }

    }

    private fun showLoadingProcess(isLoading: Boolean){
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun loadAllFollowers(followers: List<FollowResponse>){
        val followersAdapter = FollowAdapter()
        followersAdapter.submitList(followers)
        binding.rvSearchUsers.adapter = followersAdapter

    }
}