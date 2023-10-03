package com.irfan.mysubmission1.Presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.irfan.mysubmission1.Presentation.ViewModel.ThemeViewModel
import com.irfan.mysubmission1.Presentation.ViewModel.ThemeViewModelFactory
import com.irfan.mysubmission1.SettingPreferences
import com.irfan.mysubmission1.dataStore
import com.irfan.mysubmission1.databinding.ActivityThemeBinding

class ThemeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchTheme: SwitchMaterial = binding.switchTheme

        val pref = SettingPreferences.getInstance(application.dataStore)
        val mainViewModel = ViewModelProvider(this, ThemeViewModelFactory(pref)).get(ThemeViewModel::class.java)

        mainViewModel.getThemeSetting().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }



        switchTheme.setOnCheckedChangeListener { _, isChecked: Boolean ->
            mainViewModel.saveThemeSetting(isChecked)
        }
    }
}