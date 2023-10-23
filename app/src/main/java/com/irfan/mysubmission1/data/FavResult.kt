package com.irfan.mysubmission1.data

sealed class FavResult<out R> private constructor() {
    data class Success<out T>(val data: T) : FavResult<T>()
    data class Error(val error: String) : FavResult<Nothing>()
    object Loading : FavResult<Nothing>()
}