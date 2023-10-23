package com.irfan.mysubmission1.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteData::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    companion object {
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null
        @JvmStatic
        fun getDatabase(context: FavoriteDao): FavoriteDatabase {
            if (INSTANCE != null) {
                return INSTANCE as FavoriteDatabase
            }
            else {
                var instance : FavoriteDatabase = synchronized(FavoriteDatabase::class.java) {
                    Room.databaseBuilder(context.applicationContext,
                        FavoriteDatabase::class.java, "fav_db")
                        .build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }

}