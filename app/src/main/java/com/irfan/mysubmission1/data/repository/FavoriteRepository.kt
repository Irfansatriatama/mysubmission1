package com.irfan.mysubmission1.data.repository

import androidx.lifecycle.LiveData
import com.irfan.mysubmission1.data.db.FavoriteDao
import com.irfan.mysubmission1.data.db.FavoriteData
import com.irfan.mysubmission1.data.db.FavoriteDatabase
import com.irfan.mysubmission1.data.retrofit.ApiService
import com.irfan.mysubmission1.data.util.AppExecutors
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: FavoriteDao) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = FavoriteDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }
    fun loadAll(): LiveData<MutableList<FavoriteData>> = mFavoriteDao.loadAll()
    fun insert(favorite: FavoriteData) {
        executorService.execute { mFavoriteDao.insert(favorite) }
    }
    fun delete(favorite: FavoriteData) {
        executorService.execute { mFavoriteDao.delete(favorite) }
    }
    fun update(favorite: FavoriteData) {
        executorService.execute { mFavoriteDao.update(favorite) }
    }

    suspend fun findFavoriteById(favoriteId: Int): FavoriteData? {
        return mFavoriteDao.findFavoriteById(favoriteId)
    }

    companion object {
        fun getInstance(
            apiService: ApiService,
            dao: FavoriteDao,
            appExecutors: AppExecutors
        ): FavoriteRepository {
            TODO("Not yet implemented")
        }
    }
}