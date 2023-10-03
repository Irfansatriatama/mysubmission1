package com.irfan.mysubmission1.Presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.irfan.mysubmission1.Presentation.Adapter.FollowAdapter
import com.irfan.mysubmission1.Presentation.ViewModel.FollowViewModel
import com.irfan.mysubmission1.R
import com.irfan.mysubmission1.data.response.FollowResponse
import com.irfan.mysubmission1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val followViewModel by viewModels<FollowViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibFavorite.setOnClickListener {
            val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
            startActivity(intent)
        }

        val ibSetting = binding.ibSetting

        ibSetting.setOnClickListener {
            val popupMenu = PopupMenu(this, ibSetting)
            popupMenu.inflate(R.menu.theme_setting) // Inflate menu to popup menu
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_theme -> {
                        val intent = Intent(this, ThemeActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }


        if (followViewModel.followers.value == null && followViewModel.followings.value == null){
            followViewModel.getDetailFollower(myUsername)
            followViewModel.getListFollow(myUsername)
        }

        followViewModel.isLoading.observe(this) { loader ->
            showLoading(loader)
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

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }

    private fun loadAllFollowers(followers: List<FollowResponse>){
        val followersAdapter = FollowAdapter()
        followersAdapter.submitList(followers)
        binding.rvSearchUsers.adapter = followersAdapter

    }

    companion object {

        private const val myUsername = "a"
    }
}