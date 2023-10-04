package com.irfan.mysubmission1.Presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.irfan.mysubmission1.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val githubSplash = binding.githubsplash

        githubSplash.alpha = 0f
        githubSplash.animate().setDuration(1500).alpha(1f).withEndAction {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(com.irfan.mysubmission1.R.anim.fade_in, com.irfan.mysubmission1.R.anim.fade_out)
            finish()
        }
    }
}