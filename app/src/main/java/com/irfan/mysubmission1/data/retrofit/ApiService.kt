package com.irfan.mysubmission1.data.retrofit

import com.irfan.mysubmission1.data.response.DetailUserResponse
import com.irfan.mysubmission1.data.response.FollowResponse
import com.irfan.mysubmission1.data.response.SearchResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("users/{username}/followers")
    fun getFollower(
        @Path("username") username: String
    ): Call<List<FollowResponse>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<FollowResponse>>

    @GET("search/users")
    fun getFollowerByUsername(
        @Query("q") username: String
    ): Call<SearchResponse>


    @GET("users/{username}")
    fun getDetailDataFollower(
        @Path("username") username: String
    ): Call<DetailUserResponse>
}

