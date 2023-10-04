package com.irfan.mysubmission1.Presentation.ViewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.irfan.mysubmission1.data.db.FavoriteDao
import com.irfan.mysubmission1.data.db.FavoriteData
import com.irfan.mysubmission1.data.db.FavoriteDatabase
import com.irfan.mysubmission1.data.repository.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val db: FavoriteDatabase) : ViewModel() {
    private val mNoteRepository: FavoriteRepository = FavoriteRepository(Application())

    val succesResult = MutableLiveData<Boolean>()
    val deleteResult = MutableLiveData<Boolean>()

    private var isFavorite = false

    fun setFavorite(item: FavoriteData) {
        mNoteRepository.insert(item)
    }

    fun findFavoriteByUsername(username : String) : FavoriteData{
        return mNoteRepository.getFavoriteByUsername(username)
    }
    fun findFavorite(id: Int) {

    }
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

    class Factory(private val db: FavoriteDatabase) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = FavoriteViewModel(db) as T
    }
}