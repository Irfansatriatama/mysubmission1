package com.irfan.mysubmission1.data.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.parcelize.Parcelize
@Entity(tableName = "favorite")
data class FavoriteData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int,

    @ColumnInfo(name = "avatar_url")
    val avatar_url: String,

    @ColumnInfo(name = "login")
    val login: String,
) : Parcelable