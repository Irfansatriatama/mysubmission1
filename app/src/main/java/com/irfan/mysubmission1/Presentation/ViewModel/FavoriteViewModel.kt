package com.irfan.mysubmission1.Presentation.ViewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.irfan.mysubmission1.data.db.FavoriteData
import com.irfan.mysubmission1.data.db.FavoriteDatabase
import com.irfan.mysubmission1.data.repository.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val db: FavoriteDatabase) : ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(db.favoriteDao())

    val successResult = MutableLiveData<Boolean>()
    val deleteResult = MutableLiveData<Boolean>()

    private var isFavorite = false

    fun setFavorite(item: FavoriteData) {
        viewModelScope.launch {
            val existingItem = mFavoriteRepository.loadAll()

            if (existingItem != null) {
                mFavoriteRepository.delete(existingItem)
                successResult.postValue(false)
            } else {
                mFavoriteRepository.insert(item)
                successResult.postValue(true)
            }
        }
    }

    fun findFavorite(id: Int): FavoriteData? {
        return mFavoriteRepository.findFavoriteById(id)
    }

    fun update(favoriteData: FavoriteData) {
        viewModelScope.launch {
            mFavoriteRepository.update(favoriteData)
            successResult.postValue(true) // Update berhasil
        }
    }

    fun delete(favoriteData: FavoriteData) {
        viewModelScope.launch {
            mFavoriteRepository.delete(favoriteData)
            successResult.postValue(true) // Hapus berhasil
        }
    }

    fun loadAll(favoriteData: FavoriteData) {
        mFavoriteRepository.loadAll()
    }

    class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FavoriteViewModel(application) as T
        }
    }
}
