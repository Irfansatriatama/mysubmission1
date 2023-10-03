package com.irfan.mysubmission1.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irfan.mysubmission1.data.db.DatabaseModule

class FavoriteViewModel(private val dbModule: DatabaseModule) : ViewModel() {

    fun getUserFavorite() = dbModule.userDAO.loadAll()

    class Factory(private val db: DatabaseModule) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = FavoriteViewModel(db) as T
    }
}