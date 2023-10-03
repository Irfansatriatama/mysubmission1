package com.irfan.mysubmission1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.irfan.mysubmission1.data.response.FollowResponse

@Dao
interface UserDatabaseAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: FollowResponse)

    @Query("SELECT * FROM User")
    fun loadAll(): LiveData<MutableList<FollowResponse>>

    @Query("SELECT * FROM User WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): FollowResponse
    @Delete
    fun delete(user: FollowResponse)
}