package com.irfan.mysubmission1.Presentation.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.irfan.mysubmission1.Presentation.Adapter.SectionAdapter
import com.irfan.mysubmission1.Presentation.ViewModel.FavoriteViewModel
import com.irfan.mysubmission1.Presentation.ViewModel.FavoriteViewModelFactory
import com.irfan.mysubmission1.Presentation.ViewModel.FollowViewModel
import com.irfan.mysubmission1.R
import com.irfan.mysubmission1.changeIconColor
import com.irfan.mysubmission1.data.db.FavoriteDao
import com.irfan.mysubmission1.data.db.FavoriteData
import com.irfan.mysubmission1.data.db.FavoriteDatabase
import com.irfan.mysubmission1.data.response.DetailUserResponse
import com.irfan.mysubmission1.data.response.FollowResponse
import com.irfan.mysubmission1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val followViewModel by viewModels<FollowViewModel>()
    // Gak kek gini : itu view model kamu ada parameter private val favoriteViewModel by viewModels<FavoriteViewModel>()
    private lateinit var currentUser : DetailUserResponse

    private lateinit var favoriteFactory : FavoriteViewModelFactory
    private lateinit var favoriteViewModel : FavoriteViewModel
    companion object {

        private val TAB_TITLES = intArrayOf(
            R.string.follower,
            R.string.following
        )

        const val KEY_TAG ="USERNAME"
        private var _username = ""
        fun getUsername() = _username


    }
    private fun setupFavorite() {
        favoriteFactory = FavoriteViewModelFactory(FavoriteDatabase.getDatabase(this))
        favoriteViewModel = ViewModelProvider(this, favoriteFactory).get(FavoriteViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFavorite()
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

        binding.btnFav.setOnClickListener {
            val user = FavoriteData(0, currentUser.avatarUrl, currentUser.login)
            // val isFavorited : Boolean = favoriteViewModel.findFavoriteByUsername(user.login) != null
            favoriteViewModel.setFavorite(user)
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

        setupFavoriteButton()
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }

    private fun loadDetailDataUser(user: DetailUserResponse) {
        currentUser = user
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

    private fun setupFavoriteButton(isBookmarked: Boolean) {
        val ivBookmark = binding.btnFav

        if (isBookmarked) {
            ivBookmark.setImageDrawable(ContextCompat.getDrawable(ivBookmark.context, R.drawable.ic_favorite))
        } else {
            ivBookmark.setImageDrawable(ContextCompat.getDrawable(ivBookmark.context, R.drawable.ic_favorite_border))
        }

        var isFavorite = isBookmarked

        ivBookmark.setOnClickListener {
            if (isFavorite) {
                // Item sudah difavoritkan
                favoriteViewModel.delete()
                isFavorite = false
            } else {
                // Item belum difavoritkan
                favoriteViewModel.insert()
                isFavorite = true
            }

            // Set ulang ikon favorit berdasarkan status saat ini
            if (isFavorite) {
                ivBookmark.setImageDrawable(ContextCompat.getDrawable(ivBookmark.context, R.drawable.ic_favorite))
            } else {
                ivBookmark.setImageDrawable(ContextCompat.getDrawable(ivBookmark.context, R.drawable.ic_favorite_border))
            }
        }
    }


}