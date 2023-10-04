package com.irfan.mysubmission1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.irfan.mysubmission1.data.response.FollowResponse

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: FavoriteData)

    @Query("SELECT * FROM favorite")
    fun loadAll(): LiveData<MutableList<FavoriteData>>

    @Query("SELECT * FROM favorite WHERE id LIKE :id    LIMIT 1")
    fun findById(id: Int): FavoriteData

    @Query("SELECT * FROM favorite WHERE login = :username LIMIT 1")
    fun findByUsername(username: String) : FavoriteData
    @Update
    fun update(favorite: FavoriteData)

    @Delete
    fun delete(favorite: FavoriteData)
}