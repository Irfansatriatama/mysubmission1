package com.irfan.mysubmission1.data.di

import android.content.Context
import com.irfan.mysubmission1.data.db.FavoriteDatabase
import com.irfan.mysubmission1.data.repository.FavoriteRepository
import com.irfan.mysubmission1.data.retrofit.ApiConfig
import com.irfan.mysubmission1.data.util.AppExecutors

object Injection {
    fun provideRepository(context: Context): FavoriteRepository {
        val apiService = ApiConfig.getApiService()
        val database = FavoriteDatabase.getDatabase(context)
        val dao = database.favoriteDao() // Ganti 'newsDao()' dengan 'favoriteDao()'
        val appExecutors = AppExecutors()
        return FavoriteRepository.getInstance(apiService, dao, appExecutors)
    }
}