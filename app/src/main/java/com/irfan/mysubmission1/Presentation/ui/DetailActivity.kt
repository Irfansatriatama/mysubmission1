package com.irfan.mysubmission1.Presentation.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.irfan.mysubmission1.Presentation.Adapter.SectionAdapter
import com.irfan.mysubmission1.Presentation.ViewModel.FollowViewModel
import com.irfan.mysubmission1.R
import com.irfan.mysubmission1.data.response.DetailUserResponse
import com.irfan.mysubmission1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val followViewModel by viewModels<FollowViewModel>()

    companion object {

        private val TAB_TITLES = intArrayOf(
            R.string.follower,
            R.string.following

        )

        const val KEY_TAG ="USERNAME"
        private var _username = ""
        fun getUsername() = _username

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _username = if (Build.VERSION.SDK_INT >= 33) {
            intent.getStringExtra(KEY_TAG).toString()
        } else {
            @Suppress("DEPRECATION")
            intent.getStringExtra(KEY_TAG).toString()
        }

        supportActionBar?.hide()

        if (_username != "") {
            // Load My Data Followers
            if (followViewModel.detailUsers.value == null) {
                followViewModel.getDetailFollower(_username)
            }
            followViewModel.isLoading.observe(this) { loader ->
                showLoading(loader)
            }

            followViewModel.detailUsers.observe(this) { follower ->
                if (follower != null) {
                    loadDetailDataUser(follower)
                }
            }
        }


        val sectionsPagerAdapter = SectionAdapter(this)
        sectionsPagerAdapter.appName = resources.getString(R.string.app_name)
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.apply {
                text = getString(TAB_TITLES[position])
            }
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }

    private fun loadDetailDataUser(user: DetailUserResponse) {
        Glide.with(this)
            .load(user.avatarUrl)
            .into(binding.imgDetail)
        binding.apply {
            tvName.text = user.login
            tvUsername.text = user.name
            tvNumFollower.text = user.followers.toString()
            tvNumFollowing.text = user.following.toString()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}