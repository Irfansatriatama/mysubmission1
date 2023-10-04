package com.irfan.mysubmission1.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irfan.mysubmission1.data.db.FavoriteDatabase

class FavoriteViewModelFactory(private val favoriteDatabase: FavoriteDatabase) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(favoriteDatabase) as T
        }

        throw IllegalArgumentException("Class is not found")
    }
}