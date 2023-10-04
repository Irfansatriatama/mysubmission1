package com.irfan.mysubmission1.Presentation.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.irfan.mysubmission1.data.db.FavoriteData
import com.irfan.mysubmission1.data.repository.FavoriteRepository

class FavoriteViewModel(application: Application) : ViewModel() {
    private val mNoteRepository: FavoriteRepository = FavoriteRepository(application)
    fun insert(favoriteData: FavoriteData) {
        mNoteRepository.insert(favoriteData)
    }
    fun update(favoriteData: FavoriteData) {
        mNoteRepository.update(favoriteData)
    }
    fun delete(favoriteData: FavoriteData) {
        mNoteRepository.delete(favoriteData)
    }

    fun loadAll(favoriteData: FavoriteData){
        mNoteRepository.loadAll()
    }
}