package com.irfan.mysubmission1.Presentation.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.irfan.mysubmission1.SettingPreferences
import kotlinx.coroutines.launch

class ThemeViewModel (private val pref: SettingPreferences): ViewModel() {
    fun getThemeSetting(): LiveData<Boolean> {
        return pref.getThemeeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean){
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}