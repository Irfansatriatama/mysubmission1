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

class FavoriteViewModel(application: Application) : ViewModel() {
    private val mFavoriteRepository: FavoriteRepository
    val successResult = MutableLiveData<Boolean>()
    val deleteResult = MutableLiveData<Boolean>()

    init {
        val db = FavoriteDatabase.getDatabase(application)
        mFavoriteRepository = FavoriteRepository(db)
    }

    fun setFavorite(item: FavoriteData) {
        viewModelScope.launch {
            val existingItem = mFavoriteRepository.findFavorite(item.id)

            if (existingItem != null) {
                mFavoriteRepository.delete(existingItem)
                successResult.postValue(false) // Item dihapus
            } else {
                mFavoriteRepository.insert(item)
                successResult.postValue(true) // Item ditambahkan
            }
        }
    }

    fun findFavorite(id: Int): FavoriteData? {
        return mFavoriteRepository.findFavoriteById(id)
    }

    fun update(favoriteData: FavoriteData) {
        mFavoriteRepository.update(favoriteData)
    }

    fun delete(favoriteData: FavoriteData) {
        mFavoriteRepository.delete(favoriteData)
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
