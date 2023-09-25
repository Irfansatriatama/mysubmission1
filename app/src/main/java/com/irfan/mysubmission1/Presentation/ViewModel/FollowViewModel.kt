package com.irfan.mysubmission1.Presentation.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.mysubmission1.data.response.DetailUserResponse
import com.irfan.mysubmission1.data.response.FollowResponse
import com.irfan.mysubmission1.data.response.SearchResponse
import com.irfan.mysubmission1.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel : ViewModel() {
    private val _follower = MutableLiveData<List<FollowResponse>?>()
    val followers: LiveData<List<FollowResponse>?> = _follower

    private val _following = MutableLiveData<List<FollowResponse>?>()
    val followings: LiveData<List<FollowResponse>?> = _following

    private val _detailUser = MutableLiveData<DetailUserResponse?>()
    val detailUsers: LiveData<DetailUserResponse?> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "FollowViewModel"
        private val apiService = ApiConfig.getApiService()
    }

    fun getListFollow(
        username: String = "irfansatriatama",
        option: String = "followers",
    ) {
        _isLoading.value = true
        val client = if (option == "followers") {
            apiService.getFollower(username)
        } else {
            apiService.getFollowing(username)
        }
        client.enqueue(object : Callback<List<FollowResponse>> {
            override fun onResponse(
                call: Call<List<FollowResponse>>,
                response: Response<List<FollowResponse>>,
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        if (option == "followers") {
                            _follower.postValue(responseBody)
                        } else {
                            _following.postValue(responseBody)
                        }
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<List<FollowResponse>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getDetailFollower(username: String = "irfansatriatama") {
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