package com.irfan.mysubmission1.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.irfan.mysubmission1.data.response.FollowResponse

@Database(entities = [FollowResponse::class], version = 1, exportSchema = false)
abstract class DatabaseManagers : RoomDatabase() {
    abstract fun userDao(): UserDatabaseAccess
}