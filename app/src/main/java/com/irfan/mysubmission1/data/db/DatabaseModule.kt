package com.irfan.mysubmission1.data.db

import android.content.Context
import androidx.room.Room

class DatabaseModule(private val context: Context) {
    private val db = Room.databaseBuilder(context, DatabaseManagers::class.java, "github.db")
        .allowMainThreadQueries()
        .build()

    val userDAO = db.userDao()
}