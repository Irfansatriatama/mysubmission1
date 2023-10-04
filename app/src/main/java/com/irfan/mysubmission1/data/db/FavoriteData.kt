package com.irfan.mysubmission1.data.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize



data class FavoriteData(
    val incomplete_results: Boolean,
    val items: MutableList<Item>,
    val total_count: Int
){
    @kotlinx.parcelize.Parcelize
    @Entity(tableName = "favorite")
    data class Item(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo
        val id: Int,
        @ColumnInfo(name = "avatar_url")
        val avatar_url: String,
        @ColumnInfo(name = "login")
        val login: String,
    ) : Parcelable
}