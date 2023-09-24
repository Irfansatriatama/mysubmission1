package com.irfan.mysubmission1.Presentation.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.mysubmission1.data.response.DetailUserResponse
import com.irfan.mysubmission1.data.response.SearchResponse
import com.irfan.mysubmission1.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel : ViewModel() {
    private val _follower = MutableLiveData<List<FollowViewModel>?>()
    val followers: LiveData<List<FollowViewModel>?> = _follower

    private val _following = MutableLiveData<List<FollowViewModel>?>()
    val followings: LiveData<List<FollowViewModel>?> = _following

    private val _detailUser = MutableLiveData<DetailUserResponse?>()
    val detailUsers: LiveData<DetailUserResponse?> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _total = MutableLiveData(0)
    val total: LiveData<Int> = _total

    companion object {
        private const val TAG = "FollowViewModel"
        private val apiService = ApiConfig.getApiService()
    }

    fun getListFollowResponse(
        username: String = "irfansatriatama",
        option: String = "followers",
    ) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailDataFollower(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>,
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _detailUser.postValue(responseBody)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getResultBySearchUsername(username: String = "") {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowerByUsername(username)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>,
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _follower.postValue(responseBody.items)
                        _total.postValue(responseBody.totalCount)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }


}